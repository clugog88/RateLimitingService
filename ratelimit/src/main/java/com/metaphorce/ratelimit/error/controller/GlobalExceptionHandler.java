package com.metaphorce.ratelimit.error.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.metaphorce.ratelimit.error.exceptions.TooManyRequestsException;
import com.metaphorce.ratelimit.error.exceptions.UserNotFoundException;
import com.metaphorce.ratelimit.error.exceptions.WrongDataException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final String ERROR_LABEL = "error";

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<Map<String, String>> handleTooManyRequests(TooManyRequestsException ex, WebRequest request){
        Map<String, String> error = new HashMap<>();
        error.put( ERROR_LABEL , ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.TOO_MANY_REQUESTS);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex, WebRequest request){
        Map<String, String> error = new HashMap<>();
        error.put( ERROR_LABEL , ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(WrongDataException.class)
    public ResponseEntity<Map<String, String>> handleWrongData(WrongDataException ex, WebRequest request){
        Map<String, String> error = new HashMap<>();
        error.put( ERROR_LABEL , ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex, WebRequest request) {
    	Map<String, String> error = new HashMap<>();
        error.put( ERROR_LABEL , ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
