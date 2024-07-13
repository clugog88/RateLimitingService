package com.metaphorce.ratelimit.users.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
        super("User not found.");
    }
    
}
