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
public class RequestAdminRateLimitInformation {
	
	@NotBlank(message = "User id is mandatory")
	private Integer userId;
	
}
