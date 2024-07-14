package com.metaphorce.ratelimit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
        super("User not found.");
    }
    
}
