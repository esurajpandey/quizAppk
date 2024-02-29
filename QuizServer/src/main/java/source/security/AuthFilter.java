package source.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import source.dtos.response.ApiResponse;
import source.services.impl.UserServiceImpl;
import source.utils.JwtUtils;

@Component
public class AuthFilter extends OncePerRequestFilter{

	@Autowired
    private JwtUtils jwtUtils;

	@Autowired
    private UserServiceImpl userService;
	
	@Autowired
	private Gson gson;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
        String token= null;
        String userName = null;
        
        String path = request.getRequestURL().toString();
        response.setContentType("application/json");
        
        if (!path.matches(".*/auth/.*") && (authHeader == null || !authHeader.startsWith("Bearer "))) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	ApiResponse ex = new ApiResponse("Authoriztion token is missing/invaid",401);
        	String errorMessage = gson.toJson(ex);
        	response.getWriter().write(errorMessage);
            return;
        }
         
        
//        if (authHeader == null && !) {
//        	 // HTTP 401.
//        	String errorMessage = "{\"error\": \"Unauthorized access. Please login.\"}";
//        	response.getWriter().write(errorMessage);
//            return;
//        }
       
        
        if (!path.matches(".*/auth/.*")) {
        	try {
            	if(authHeader !=null && authHeader.startsWith("Bearer")){
                    token = authHeader.substring(7);
                    userName =jwtUtils.extractUserName(token);
                }
                if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails = userService.loadUserByUsername(userName);
                    if(jwtUtils.validateToken(token,userDetails)){
                        UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
    		} catch (MalformedJwtException ex) {
    			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	ApiResponse error = new ApiResponse("Invalid jwt token - Malfromed",401);
            	String errorMessage = gson.toJson(error);
            	response.getWriter().write(errorMessage);
                return;
    		}catch (ExpiredJwtException ex) {
    			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	ApiResponse error = new ApiResponse("Jwt token has been expired/login again",401);
            	String errorMessage = gson.toJson(error);
            	response.getWriter().write(errorMessage);
                return;
			}
        }
        
        filterChain.doFilter(request,response);
		
	}
	
	
}
