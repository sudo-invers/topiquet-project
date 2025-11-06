package com.invers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.invers.model.Users;
import com.invers.service.MyUsersDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final MyUsersDetailsService userDetailsService;
	private final Users user;

	SecurityConfig(MyUsersDetailsService userDetailsService, Users user) {
		this.userDetailsService = userDetailsService;
		this.user = user;
	}

	@Bean
	UserDetailsService userDetailsService(String username) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(userDetailsService.loadUserByUsername(username));
		return manager;

	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(Customizer -> Customizer.disable());
        http.authorizeHttpRequests(Request -> Request.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
		return http.build();
		
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		
		String username = user.getName();		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService(username));
		provider.setPasswordEncoder(MyPasswordEncoder());
		return provider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder MyPasswordEncoder(){
		return new BCryptPasswordEncoder(13); //2^13 
	}

}
