package com.app.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.UserFoundException;
import com.app.model.User;
import com.app.model.UserRole;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUserName(user.getUsername());
		
		if(local != null) {
			System.out.println("User already present !!!");
			throw new UserFoundException();
		}else 
		{
			// create user
			for (UserRole ur: userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		
		return local;
		
	}


	//getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUserName(username);
	}

	// deleting user by userId
	@Override
	public void deleteuser(Long userId) {
		this.userRepository.deleteById(userId);
	}

//	//updating user by username
//	@Override
//	public User updateuser(String username) {
//		return this.userRepository.findByUserName(username);// wrong implementation
//	}
}
