package com.metaphorce.ratelimit.configuration.aspects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.metaphorce.ratelimit.configuration.properties.RateLimitProperties;
import com.metaphorce.ratelimit.error.exceptions.TooManyRequestsException;

@Aspect
@Component
public class RateLimitAspect {

    private final ConcurrentHashMap<String, List<Long>> requestCounts = new ConcurrentHashMap<>();

    @Autowired
	private RateLimitProperties rateLimitProperties;
    
    @Before("@annotation(com.metaphorce.ratelimit.security.interfaces.WithRateLimitProtection)")
    public void rateLimit() {
        final ServletRequestAttributes requestAttributes = 
        		(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final String key = requestAttributes.getRequest().getRemoteAddr();
        final long currentTime = System.currentTimeMillis();
        
        requestCounts.putIfAbsent(key, new ArrayList<>());
        requestCounts.get(key).add(currentTime);
        cleanUpRequestCounts(currentTime);
        
        if (requestCounts.get(key).size() > rateLimitProperties.getRateLimit()) {
            throw new TooManyRequestsException();
        }
    }

    private void cleanUpRequestCounts(final long currentTime) {
        requestCounts.values().forEach(l -> {
            l.removeIf(t -> timeIsTooOld(currentTime, t));
        });
    }

    private boolean timeIsTooOld(final long currentTime, final long timeToCheck) {
        return (currentTime - timeToCheck) > rateLimitProperties.getRateDuration();
    }
}