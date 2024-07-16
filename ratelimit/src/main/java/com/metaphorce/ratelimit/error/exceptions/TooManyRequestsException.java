package com.metaphorce.ratelimit.error.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class TooManyRequestsException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public TooManyRequestsException(String message){
        super("Too many requests.");
    }

}
