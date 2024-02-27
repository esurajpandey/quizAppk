package source.utils;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import source.models.User; 
@Component
public class JwtUtils {
private static final String SECERET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECERET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	public String createToken (Map<String, String> claims,String email,String id) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setId(id)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateToken(User user) {
		Map<String, String> claim = new HashMap<String, String>();
		claim.put("name", user.getName());
		claim.put("email", user.getEmail());
		claim.put("id",user.getId());
		
		return this.createToken(claim,user.getEmail(),user.getId() );
//		String token = 
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}
	
	private <T>T extractClaim (String token,Function<Claims,T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
