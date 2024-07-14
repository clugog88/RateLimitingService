package com.metaphorce.ratelimit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests( auth -> auth
            		.requestMatchers("/users/**").permitAll()
            		.requestMatchers("/admin/**").permitAll()
            		.anyRequest().denyAll()
            )
            .sessionManagement( session -> session
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        return http.build();
    }
    
}
