package com.metaphorce.ratelimit.model.repositories;

import com.metaphorce.ratelimit.model.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
