package com.manish.sercurity;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manish.entity.UserEntity;
import com.manish.repo.UserRepo;
import com.manish.service.UserService;

public class CustomUserDetailsService implements UserDetailsService {

	public CustomUserDetailsService(UserService userService) {
		
	}
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("User Not Found " + email));

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
				);
	}
	


}
