package com.metaphorce.ratelimit.domain.controllers.model;

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
public class RequestUserAdd {
	
	private String name;
    private String lastname;
    private String email;
    private String phone;
    private Integer age;
	private String role;
	
}
