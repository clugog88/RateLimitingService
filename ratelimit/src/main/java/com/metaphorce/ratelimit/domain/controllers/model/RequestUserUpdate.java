package com.metaphorce.ratelimit.domain.controllers.model;

import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Lastname is mandatory")
    private String lastname;
    
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Phone is mandatory")
    private String phone;
	
	@NotBlank(message = "Age is mandatory")
    private Integer age;
	
	@NotBlank(message = "Role is mandatory")
	private String role;
	
}
