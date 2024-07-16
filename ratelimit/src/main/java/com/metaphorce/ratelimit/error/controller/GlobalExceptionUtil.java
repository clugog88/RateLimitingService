package com.metaphorce.ratelimit.error.controller;

import org.apache.logging.log4j.Logger;

import com.metaphorce.ratelimit.error.exceptions.AppException;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class GlobalExceptionUtil {
	
	private GlobalExceptionUtil() {
		super();
	}
	
	public static void handleException(Logger log, Exception e) throws AppException {
		if(e instanceof AppException) {
			log.info( e.getMessage() );
			throw (AppException) e;
		}
	}

}
