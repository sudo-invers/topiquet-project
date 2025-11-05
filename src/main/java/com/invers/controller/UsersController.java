package com.invers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invers.model.Users;
import com.invers.service.UsersService;

@RestController
@RequestMapping("/topiquet")
public class UsersController {
	
	private final UsersService service;
	
	public UsersController(UsersService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public Users register(Users user) {
		return service.register(user);
	}
	
	@GetMapping("/users")
	public Users getAllUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public Users getUserById(@PathVariable long id) {
		return service.getUserById(id);
	}

}
