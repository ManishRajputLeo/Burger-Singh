package com.manish.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manish.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByEmail(String email);
	

}
	