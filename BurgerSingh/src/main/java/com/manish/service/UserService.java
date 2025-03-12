package com.manish.service;

import java.util.Optional;

import com.manish.entity.UserEntity;

public interface UserService {
	
	Optional<UserEntity> findByEmail(String email);
	
	void saveUser(UserEntity newUser);

}
