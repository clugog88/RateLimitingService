package com.metaphorce.ratelimit.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.metaphorce.ratelimit.error.exceptions.UserNotFoundException;
import com.metaphorce.ratelimit.persistence.entities.User;
import com.metaphorce.ratelimit.persistence.repositories.UserRepository;
import com.metaphorce.ratelimit.security.model.UserDetailsImpl;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByEmail( username );
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException();
        }
        
        User user = userOpt.get();
        return new UserDetailsImpl( user, user.getRole() );
    }
    
}
