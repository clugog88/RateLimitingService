package com.metaphorce.ratelimit.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.ratelimit.persistence.entities.User;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);

}
