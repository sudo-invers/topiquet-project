package com.invers.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.invers.model.MyUserDetails;
import com.invers.model.Users;
import com.invers.repository.UsersRepository;

public class MyUsersDetailsService implements UserDetailsService {

	private final UsersRepository repo;

	public MyUsersDetailsService(UsersRepository repo, Users user ) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {

		Users user = repo.findByUserName(username);

		if (username == null) {
			throw new UsernameNotFoundException("User '" + user + "' not found") ;
		} else {
			return new MyUserDetails(user);
		}
	}

}
