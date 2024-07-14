package com.metaphorce.ratelimit.users.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metaphorce.ratelimit.exceptions.UserNotFoundException;
import com.metaphorce.ratelimit.exceptions.WrongDataException;
import com.metaphorce.ratelimit.persistence.entities.User;
import com.metaphorce.ratelimit.persistence.entities.enums.UserRoleEnum;
import com.metaphorce.ratelimit.persistence.repositories.UserRepository;
import com.metaphorce.ratelimit.users.controllers.model.RequestUserAdd;
import com.metaphorce.ratelimit.users.controllers.model.RequestUserUpdate;
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
	public User getById(Long id) {
		Optional<User> userOpt = userRepository.findById( id );
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException();
		}
		return userOpt.get();
	}

	@Override
	public User add(RequestUserAdd requestDto) {
		final User user = new User();
		
		user.setName( requestDto.getName() );
		user.setLastname( requestDto.getLastname() );
		user.setEmail( requestDto.getEmail() );
		user.setPhone( requestDto.getPhone() );
		user.setAge( requestDto.getAge() );
		
		String strHelper = requestDto.getRole();
		if(strHelper!=null) {
			try {
				UserRoleEnum role = UserRoleEnum.valueOf( strHelper );
				user.setRole( role );
			}
			catch(RuntimeException e) {
				throw new WrongDataException();
			}
		}
		else {
			user.setRole( UserRoleEnum.REGULAR );
		}
		
		user.setAge( requestDto.getAge() );
		
		userRepository.save( user );
		return user;
	}

	@Override
	public User update(Long id, RequestUserUpdate requestDto) {
		
		Optional<User> userOpt = userRepository.findById( id );
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException();
		}
		final User user = userOpt.get();
		
		user.setName( requestDto.getName() );
		user.setLastname( requestDto.getLastname() );
		user.setEmail( requestDto.getEmail() );
		user.setPhone( requestDto.getPhone() );
		user.setAge( requestDto.getAge() );
		
		String strHelper = requestDto.getRole();
		if(strHelper!=null) {
			try {
				UserRoleEnum role = UserRoleEnum.valueOf( strHelper );
				user.setRole( role );
			}
			catch(RuntimeException e) {
				throw new WrongDataException();
			}
		}
		else {
			throw new WrongDataException();
		}
		
		user.setAge( requestDto.getAge() );
		
		userRepository.save( user );
		return user;
	}

	@Override
	public void delete(Long id) {
		boolean exist = userRepository.existsById( id );
		if (!exist) {
			throw new UserNotFoundException();
		}
		userRepository.deleteById( id );
	}
    
}
