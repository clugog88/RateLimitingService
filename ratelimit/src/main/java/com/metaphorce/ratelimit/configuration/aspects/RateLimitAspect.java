package com.metaphorce.ratelimit.configuration.aspects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.metaphorce.ratelimit.configuration.properties.RateLimitProperties;
import com.metaphorce.ratelimit.error.exceptions.TooManyRequestsException;
import com.metaphorce.ratelimit.persistence.entities.User;
import com.metaphorce.ratelimit.persistence.entities.enums.UserRoleEnum;
import com.metaphorce.ratelimit.security.model.UserDetailsImpl;

import jakarta.servlet.http.HttpServletRequest;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Aspect
@Component
public class RateLimitAspect {

    private final ConcurrentHashMap<Long, List<Long>> requestCounts = new ConcurrentHashMap<>();

    @Autowired
	private RateLimitProperties rateLimitProperties;
    
    @Before("@annotation(com.metaphorce.ratelimit.security.interfaces.WithRateLimitProtection)")
    public void rateLimit() {
        final ServletRequestAttributes requestAttributes = 
        		(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpServletRequest httpRequest = requestAttributes.getRequest();
        
        final UsernamePasswordAuthenticationToken authToken = 
        		(UsernamePasswordAuthenticationToken) httpRequest.getUserPrincipal();
        final Collection<GrantedAuthority> rolesList = authToken.getAuthorities();
        
        boolean hasPremium = false;
        boolean hasRegular = false;
        if(rolesList!=null && !rolesList.isEmpty()) {
        	hasPremium = rolesList.contains( UserRoleEnum.PREMIUM );
        	hasRegular = rolesList.contains( UserRoleEnum.REGULAR );
        }
        
        final UserDetailsImpl principal = (UserDetailsImpl) authToken.getPrincipal();
        final User user = principal.getUser();
        final Long key = user.getId();
        
//        final String key = httpRequest.getRemoteAddr();
        requestCounts.putIfAbsent(key, new ArrayList<>());
        
        final long currentTime = System.currentTimeMillis();
        requestCounts.get(key).add(currentTime);
        
        requestCountCleanup(currentTime);
        
        if(hasPremium || hasRegular) {
        	int requestNum = requestCounts.get(key).size();
        	if(hasPremium) {
            	if (requestNum > rateLimitProperties.getRateLimitForPremiumRole() ) {
                    throw new TooManyRequestsException();
                }
            }
            else if (requestNum > rateLimitProperties.getRateLimitForRegularRole() ) {
                throw new TooManyRequestsException();
            }
        }
        else {
        	throw new TooManyRequestsException();
        }
        
    }

    /**
     * Request Count Cleanup
     * @param currentTime Current time to compare.
     */
    private void requestCountCleanup(final long currentTime) {
        requestCounts.values().forEach(timesList -> {
        	timesList.removeIf(time -> timeIsTooOld(currentTime, time));
        });
    }

    /**
     * Evaluates if the saved time is within the duration configured as a limit.
     * @param currentTime Current time to compare.
     * @param timeToCheck Time to evaluate.
     * @return Evaluation result.
     */
    private boolean timeIsTooOld(final long currentTime, final long timeToCheck) {
        return (currentTime - timeToCheck) > rateLimitProperties.getRateDuration();
    }
    
    /**
     * Get request number by user Id.
     * @param userId User id.
     * @return Request number.
     */
    public int getRequestNumberByUserId(Long userId) {
    	List<Long> timesList = requestCounts.get(userId);
    	if(timesList!=null && !timesList.isEmpty()) {
    		return timesList.size();
    	}
    	return 0;
    }
    
}
