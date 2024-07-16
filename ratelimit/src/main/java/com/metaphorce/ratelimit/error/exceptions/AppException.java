package com.metaphorce.ratelimit.error.exceptions;

import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@NoArgsConstructor
public abstract class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	protected AppException(String message) {
        super( message );
    }
    
	protected AppException(String message, Throwable cause) {
        super( message, cause );
    }
	
}
