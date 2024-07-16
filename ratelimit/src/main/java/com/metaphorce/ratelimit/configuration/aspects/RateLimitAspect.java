package com.metaphorce.ratelimit.configuration.aspects;

//import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import com.metaphorce.ratelimit.configuration.properties.RateLimitProperties;
import com.metaphorce.ratelimit.persistence.entities.enums.UserRoleEnum;

import jakarta.servlet.http.HttpServletRequest;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Aspect
@Component
public class RateLimitAspect {

//    private final ConcurrentHashMap<String, List<Long>> requestCounts = new ConcurrentHashMap<>();
//
//    @Autowired
//	private RateLimitProperties rateLimitProperties;
    
    @Before("@annotation(com.metaphorce.ratelimit.security.interfaces.WithRateLimitProtection)")
    public void rateLimit() {
        final ServletRequestAttributes requestAttributes = 
        		(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpServletRequest httpRequest = requestAttributes.getRequest();
        
        final UsernamePasswordAuthenticationToken principal = 
        		(UsernamePasswordAuthenticationToken) httpRequest.getUserPrincipal();
        final Collection<GrantedAuthority> rolesList = principal.getAuthorities();
        
        boolean hasPremium = false;
        boolean hasRegular = false;
        if(rolesList!=null && !rolesList.isEmpty()) {
        	hasPremium = rolesList.contains( UserRoleEnum.PREMIUM );
        	hasRegular = rolesList.contains( UserRoleEnum.REGULAR );
        }
        
//        final String key = httpRequest.getRemoteAddr();
        
//        final long currentTime = System.currentTimeMillis();
//        requestCounts.putIfAbsent(key, new ArrayList<>());
//        requestCounts.get(key).add(currentTime);
//        cleanUpRequestCounts(currentTime);
        
//        if (requestCounts.get(key).size() > rateLimitProperties.getRateLimit() ) {
//            throw new TooManyRequestsException();
//        }
    }

//    private void cleanUpRequestCounts(final long currentTime) {
//        requestCounts.values().forEach(l -> {
//            l.removeIf(t -> timeIsTooOld(currentTime, t));
//        });
//    }

//    private boolean timeIsTooOld(final long currentTime, final long timeToCheck) {
//        return (currentTime - timeToCheck) > rateLimitProperties.getRateDuration();
//    }
}