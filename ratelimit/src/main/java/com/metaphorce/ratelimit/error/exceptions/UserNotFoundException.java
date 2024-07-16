package com.metaphorce.ratelimit.error.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class UserNotFoundException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
        super("User not found.");
    }
    
}
