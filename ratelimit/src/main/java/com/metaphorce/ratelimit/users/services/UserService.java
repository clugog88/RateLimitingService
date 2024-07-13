package com.metaphorce.ratelimit.users.services;

import java.util.List;

import com.metaphorce.ratelimit.model.pojos.User;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface UserService {

	public List<User> getList();

	public User getById(Long id);

}
