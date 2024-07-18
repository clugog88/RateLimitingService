package com.metaphorce.ratelimit.configuration.components;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.metaphorce.ratelimit.configuration.aspects.RateLimitAspect;
import com.metaphorce.ratelimit.configuration.properties.RateLimitProperties;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class CircuitBreakerFilter implements Filter { 
    
	@Autowired
	private RateLimitProperties rateLimitProperties;
	
	@Autowired
	private RateLimitAspect rateLimitAspect;
	
    @Override
    public void doFilter(ServletRequest servletRequest, 
                         ServletResponse servletResponse, 
                         FilterChain filterChain) 
        throws IOException, ServletException{ 
    	
    	final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    	final String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader!=null) {
        	
        	if(authorizationHeader.startsWith("Basic ")) {
        		String authString = authorizationHeader.substring( "Basic ".length() );
        		byte[] decodedAuth = Base64.getDecoder().decode( authString );
        		authString = new String( decodedAuth );
        		
        		String username = authString.substring( 0, authString.indexOf(":") );
        		
        		int errorRequestNum = rateLimitAspect.getRequestErrorNumberByUsername( username );
        		int circuitBreakerLimit = rateLimitProperties.getCircuitBreakerRequestNumber();
        		if(errorRequestNum >= circuitBreakerLimit) {
        			final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        			httpResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        			return;
        		}
        	}
        	
        }
        filterChain.doFilter(servletRequest, servletResponse); 
    } 

}
