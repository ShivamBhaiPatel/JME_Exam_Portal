 package com.app.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.coyote.*;

import com.app.exceptions.UserNotFoundException;
import com.app.model.Role;
import com.app.model.User;
import com.app.model.UserRole;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{
		user.setProfile("default.png");
		
		//encoding password with bCryptPasswordEncoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		
		role.setRoleId(102L);
		role.setRoleName("User");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userService.createUser(user,roles);
	}
	
	//get user details by user id
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		return this.userService.getUser(username);
	}
	
	//updating user detail by user name
//	@PostMapping("{userName")
//	public User updateUser(@PathVariable("username") String username)
//	{
//		return this.userService.updateuser(username);
//	}
	
	//delete user by user id
	@DeleteMapping("/{userId}")
	public void deleteuser(@PathVariable("userId") Long userId)
	{
		this.userService.deleteuser(userId);
	}
	
	
	// update ApI
	
	@ExceptionHandler(UserNotFoundException.class)
	public  ResponseEntity<?> exceptionHandler(UserNotFoundException ex) {
	    return new ResponseEntity<>
	    ("Hey, Customer, error ocuured during execution", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
