package com.metaphorce.ratelimit.configuration.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.metaphorce.ratelimit.persistence.entities.User;
import com.metaphorce.ratelimit.persistence.entities.enums.UserRoleEnum;
import com.metaphorce.ratelimit.persistence.repositories.UserRepository;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class ApplicationStartup {
	
	private static final Logger log = LogManager.getLogger(ApplicationStartup.class);
	
	@Autowired
    private UserRepository userRepository;
	
	@EventListener
    public void handleSuccessful(ApplicationStartedEvent event) {
		log.info("Se cargan datos iniciales de prueba.");
        
		User user = new User();
        user.setName( "Christhian" );
        user.setLastname( "Lugo" );
        user.setEmail( "christhian.lugo@gmail.com" );
        user.setPhone( "428-123-1234" );
        user.setAge( 36 );
        user.setRole( UserRoleEnum.PREMIUM );
        userRepository.save( user );
        
        user = new User();
        user.setName( "Cristina" );
        user.setLastname( "Govea" );
        user.setEmail( "cristina.govea@gmail.com" );
        user.setPhone( "428-123-1235" );
        user.setAge( 59 );
        user.setRole( UserRoleEnum.REGULAR );
        userRepository.save( user );
		
        user = new User();
        user.setName( "Juan" );
        user.setLastname( "Lugo" );
        user.setEmail( "juan.lugo@gmail.com" );
        user.setPhone( "428-123-1236" );
        user.setAge( 60 );
        user.setRole( UserRoleEnum.REGULAR );
        userRepository.save( user );
    }
	
}
