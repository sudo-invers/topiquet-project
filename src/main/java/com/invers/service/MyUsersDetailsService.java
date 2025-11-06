package com.invers.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.invers.model.MyUserDetails;
import com.invers.model.Users;
import com.invers.repository.UsersRepository;

@Service
public class MyUsersDetailsService implements UserDetailsService {

	private final UsersRepository repo;

	public MyUsersDetailsService(UsersRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String name) {

		Users user = repo.findByName(name);

		if (name == null) {
			throw new UsernameNotFoundException("User '" + user + "' not found") ;
		} else {
			return new MyUserDetails(user);
		}
	}

}
