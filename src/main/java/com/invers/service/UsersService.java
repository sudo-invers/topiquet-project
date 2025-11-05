package com.invers.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.invers.model.Users;
import com.invers.repository.UsersRepository;

@Service
@RequestMapping("/topiquet")
public class UsersService {
	
	public UsersService(UsersRepository repo) {
		this.repo = repo;
	}

	private final UsersRepository repo;
	
	public Users register(Users user) {
		
		return repo.save(user);
	}
	
	public Users getUserById(Long id) {
		
		if (repo.existsById(id) == true) {
			return repo.getReferenceById(id);
		}
		else {
			return null;
		}
	}
	
	public Users getAllUsers() {
		return (Users) repo.findAll();
	}

}
