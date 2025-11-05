package com.invers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invers.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	//Find the username to implement it in the MyUserDetailsService;
	Users findByUserName(String username);

}
