package com.metaphorce.ratelimit.domain.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaphorce.ratelimit.configuration.aspects.RateLimitAspect;
import com.metaphorce.ratelimit.domain.controllers.model.RequestAdminRateLimitInformation;
import com.metaphorce.ratelimit.error.controller.GlobalExceptionUtil;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static final Logger log = LogManager.getLogger(AdminController.class);
	
	@Autowired
	private RateLimitAspect rateLimitAspect;
	
	@PostMapping(value = "/rate-limit-information")
	public ResponseEntity<Map<String, Integer>> rateLimitInformation(@RequestBody RequestAdminRateLimitInformation requestDto){
		log.info("Lanzando el guardado de la informacion.");
		try {
			int requestNum = rateLimitAspect.getRequestNumberByUserId( 
						requestDto.getUserId().longValue()
					);
			
			final Map<String, Integer> message = new HashMap<>();
			message.put( "numberOfRequests", requestNum);
			return new ResponseEntity<>(
					message, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error guardando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
