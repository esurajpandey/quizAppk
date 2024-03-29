package source.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import source.models.Role;
import source.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	private AuthFilter authFilter;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;
	
	@Bean
    UserDetailsService userDetailsService(){
        return new UserServiceImpl();
    }
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
					    .requestMatchers("/api/auth/**").permitAll() // Allow /api/auth/ without restrictions
					    .requestMatchers("/api/user/**", "/api/products/**").permitAll() // Allow other endpoints
					    .requestMatchers("/api/admin/**").permitAll() // Require admin role for admin endpoints
					    .anyRequest().authenticated() // Require authentication for all other requests
				)
//				.httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint))
				.exceptionHandling(Customizer.withDefaults())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
			    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();				
	}
	@Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
