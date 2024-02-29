package source.services.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import exceptions.custom.NotFoundException;
import source.config.AppConstant;
import source.dtos.request.CreateUser;
import source.models.Role;
import source.models.User;
import source.repository.UserRespository;
import source.services.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRespository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByEmail(email);	
		return user.map(UserInfoDetials::new)
	                .orElseThrow(()-> new UsernameNotFoundException("User not found : "+email));
	}

	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	@Override
	public User getUserById(String id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	@Override
	public User createUser(CreateUser body) {
		
		Optional<User> isExists = userRepo.findByEmail(body.email());
		if (isExists.isPresent()) {
			//throw user is already exists
		}
		
		User user = new User();		
		user.setEmail(body.email());
		user.setName(body.name());
		user.setPassword(passwordEncoder.encode(body.password()));
		user.setRole(Role.USER);
		user.setProfile(AppConstant.DEFAULT_PROFILE_LINK);
		return user;
	}

}
