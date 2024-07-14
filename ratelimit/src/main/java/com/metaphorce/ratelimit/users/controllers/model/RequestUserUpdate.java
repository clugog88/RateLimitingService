package com.metaphorce.ratelimit.users.controllers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserUpdate {
	
	private String name;
    private String lastname;
    private String email;
    private String phone;
    private Integer age;
	private String role;
	
}
