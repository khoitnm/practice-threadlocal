package org.tnmk.practice.springboot.prometheuse.aspectj.metrics;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * By default, the @Timed doesn't supported in @Service layer. So we need this configuration to make it works.
 * Following this guideline: https://stackoverflow.com/questions/48704789/how-to-measure-service-methods-using-spring-boot-2-and-micrometer
 */
@Configuration
//We don't really need @EnableAspectJAutoProxy
public class TimedAspectConfig {
    @Bean
    TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
