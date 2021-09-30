package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.exceptions.UserNotFoundException;
import com.app.model.User;
import com.app.repository.UserRepository;

@Service // since we are making  autowire
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		User user = this.userRepository.findByUserName(username);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}



}
