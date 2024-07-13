package com.metaphorce.ratelimit.users.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.metaphorce.ratelimit.model.pojos.User;
import com.metaphorce.ratelimit.users.services.UserService;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	private static final Logger log = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<User>> list() {
		log.info("Lanzando la busqueda de la informacion.");
		try {
			List<User> list = userService.getList();
			return new ResponseEntity<>(
					list, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("Error buscando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		log.info("Lanzando la busqueda de la informacion.");
		try {
			User user = userService.getById( id );
			return new ResponseEntity<>(
					user, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("Error buscando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
