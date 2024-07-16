package com.metaphorce.ratelimit.users.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaphorce.ratelimit.error.controller.GlobalExceptionUtil;
import com.metaphorce.ratelimit.persistence.entities.User;
import com.metaphorce.ratelimit.security.interfaces.WithRateLimitProtection;
import com.metaphorce.ratelimit.users.controllers.model.RequestUserAdd;
import com.metaphorce.ratelimit.users.controllers.model.RequestUserUpdate;
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
	public ResponseEntity<List<User>> list() {
		log.info("Lanzando la busqueda de la informacion.");
		try {
			List<User> list = userService.getList();
			return new ResponseEntity<>(
					list, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error buscando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		log.info("Lanzando la busqueda de la informacion.");
		try {
			User user = userService.getById( id );
			return new ResponseEntity<>(
					user, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error buscando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> add(@RequestBody RequestUserAdd requestDto){
		log.info("Lanzando el guardado de la informacion.");
		try {
			final User user = userService.add( requestDto );
			return new ResponseEntity<>(
					user, 
					HttpStatus.CREATED);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error guardando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody RequestUserUpdate requestDto){
		log.info("Lanzando la actualizacion de la informacion.");
		try {
			final User user = userService.update( id, requestDto );
			return new ResponseEntity<>(
					user, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error actualizando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		log.info("Lanzando la eliminacion de la informacion.");
		try {
			userService.delete( id );
			return new ResponseEntity<>(
					HttpStatus.OK);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error eliminando la informacion. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@WithRateLimitProtection
	@GetMapping(value = "/{id}/execute-task")
	public ResponseEntity<Map<String, String>> executeTask(@PathVariable("id") Long id) {
		log.info("Lanzando la ejecucion del metodo.");
		try {
			final Map<String, String> message = new HashMap<>();
			message.put( "message", "Executed");
			return new ResponseEntity<>(
					message, 
					HttpStatus.OK);
		}
		catch (Exception e) {
			GlobalExceptionUtil.handleException(log, e);
			log.error("Error ejecutando el metodo. ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
