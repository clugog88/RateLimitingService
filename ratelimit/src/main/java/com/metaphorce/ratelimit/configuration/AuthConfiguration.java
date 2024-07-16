package com.metaphorce.ratelimit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import com.metaphorce.ratelimit.configuration.components.BasicAuthenticationProvider;
import com.metaphorce.ratelimit.security.service.UserDetailsServiceImpl;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Configuration
@EnableScheduling
public class AuthConfiguration {
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsServiceImpl) {
    	BasicAuthenticationProvider authProvider = new BasicAuthenticationProvider();
        authProvider.setUserDetailsService( userDetailsServiceImpl );
        return authProvider;
    }
    
}
