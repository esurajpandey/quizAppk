package source.services.impl;

import java.util.Arrays;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import source.models.User;


public class UserInfoDetials implements UserDetails{

	private static final long serialVersionUID = 8651837833998730553L;

	private String userEmail=null;
    private String password = null;
    List<GrantedAuthority> authorities;
    
    public UserInfoDetials(User user){
    	   userEmail= user.getEmail();
	       password= user.getPassword();
	       String roles = user.getRole().toString();
	       authorities= Arrays.stream(roles.split(","))
	               .map(SimpleGrantedAuthority::new)
	               .collect(Collectors.toList());
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
