package com.invers.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<Users> register(Users user) {
		try {
			return new ResponseEntity<Users>(service.register(user),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<List<Users>>(service.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable long id) {
			Users user = service.getUserById(id);
			
			if (user != null) {
				return new ResponseEntity<Users>(service.getUserById(id), HttpStatus.OK);
			} 
			else {
				return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
			}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> DeleteUserById(@PathVariable Long id){
		Users user = service.getUserById(id);
		if (user != null) {
			service.deleteUserById(id);
			return new ResponseEntity<String>("User deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
