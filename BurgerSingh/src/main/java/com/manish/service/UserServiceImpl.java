package com.manish.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.manish.entity.UserEntity;
import com.manish.repo.UserRepo;

	@Service
	public class UserServiceImpl implements UserService{
	
	private UserRepo userRepo;
	

	public UserServiceImpl(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@Override
	public Optional<UserEntity> findByEmail(String email) {
		return  userRepo.findByEmail(email);
	}



	public void saveUser(UserEntity newUser) {
		userRepo.save(newUser);
		
	}

}
