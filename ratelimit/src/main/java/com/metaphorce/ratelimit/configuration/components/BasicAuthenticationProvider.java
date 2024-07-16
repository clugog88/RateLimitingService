package com.metaphorce.ratelimit.configuration.components;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        
        UserDetails principal = userDetailsService.loadUserByUsername( username );
        if (principal == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
        
        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
