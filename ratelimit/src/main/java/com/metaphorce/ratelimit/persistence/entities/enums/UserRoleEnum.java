package com.metaphorce.ratelimit.persistence.entities.enums;

import org.springframework.security.core.GrantedAuthority;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public enum UserRoleEnum implements GrantedAuthority {
	
	REGULAR, 
	PREMIUM; 

	@Override
	public String getAuthority() {
		return ROLE_PREFIX + this.name();
	}
	
	private static final String ROLE_PREFIX = "ROLE_";
	
}
