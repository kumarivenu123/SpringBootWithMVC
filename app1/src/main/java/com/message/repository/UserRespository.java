package com.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.message.entity.User;

public interface UserRespository extends JpaRepository<User, String>{
	
	User findByUserName(String userName);

}
