package com.metaphorce.ratelimit.users.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metaphorce.ratelimit.model.pojos.User;
import com.metaphorce.ratelimit.model.repositories.UserRepository;
import com.metaphorce.ratelimit.users.exceptions.UserNotFoundException;
import com.metaphorce.ratelimit.users.services.UserService;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

	@Override
	public List<User> getList() {
		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) throws UserNotFoundException {
		Optional<User> userOpt = userRepository.findById( id );
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException();
		}
		return userOpt.get();
	}
    
}
