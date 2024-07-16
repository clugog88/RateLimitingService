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

	@Value("${app.rate.limit:#{200}}")
    private int rateLimit;

    @Value("${app.rate.durationinms:#{60000}}")
    private long rateDuration;

}
