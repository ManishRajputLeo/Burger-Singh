package com.manish.sercurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.manish.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private UserService userService;
	
	
	
	
	public SecurityConfig(UserService userService) {
		super();
		this.userService = userService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
	
		http
		.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/", "/register", "/about","/css/**", "/js/**", "/images/**", "/jpge/**").permitAll()
					.requestMatchers("/placeorder", "orderlist").authenticated()
					.requestMatchers("/user/**").hasAnyRole("USER")
					.anyRequest().authenticated()
					)
			.formLogin(form -> form
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.failureUrl("/login?error=true") 
					.defaultSuccessUrl("/", true)
					.permitAll()
					)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout")
					.permitAll()
					);
				
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService () {
		return new CustomUserDetailsService(userService);
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	
}