package com.app.service;

import java.util.Set;

import com.app.model.User;
import com.app.model.UserRole;

public interface UserService {
	// create user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	// get user by username
	public User getUser(String username);
	
	//delete by userid
	public void deleteuser(Long userId);
	
//	//update user by username
//	public User updateuser(String username);
	
}
