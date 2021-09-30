package com.app.exceptions;

@SuppressWarnings("serial")
public class UserFoundException extends Exception {

	public UserFoundException() {
		super("User with this username is already presenet in the database, Try with other name");
	}

}
