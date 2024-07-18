package com.metaphorce.ratelimit.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
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
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests( auth -> auth
            		.requestMatchers("/users/**").authenticated()
            		.requestMatchers("/admin/**").permitAll()
            		.anyRequest().denyAll()
            )
            .sessionManagement( session -> session
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider( authenticationProvider )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
}
