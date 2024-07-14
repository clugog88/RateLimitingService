package com.metaphorce.ratelimit.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.ratelimit.persistence.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
