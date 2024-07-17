package com.metaphorce.ratelimit.domain.services;

import java.util.List;

import com.metaphorce.ratelimit.domain.controllers.model.RequestUserAdd;
import com.metaphorce.ratelimit.domain.controllers.model.RequestUserUpdate;
import com.metaphorce.ratelimit.persistence.entities.User;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface UserService {

	public List<User> getList();
	public User getById(Long id);
	public User add(RequestUserAdd requestDto);
	public User update(Long id, RequestUserUpdate requestDto);
	public void delete(Long id);

}
