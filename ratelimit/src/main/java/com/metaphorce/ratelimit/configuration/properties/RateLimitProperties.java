package com.metaphorce.ratelimit.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@Configuration
@PropertySource("classpath:rate-limit.properties")
public class RateLimitProperties {

	@Value("${request.rate.limit.regular:#{5}}")
    private int rateLimitForRegularRole;
	
	@Value("${request.rate.limit.premium:#{10}}")
    private int rateLimitForPremiumRole;

    @Value("${request.rate.duration:#{60000}}")
    private long rateDuration;

}
