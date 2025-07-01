package fr.marketsim.application.config;

import fr.marketsim.application.service.utilities.UuidGenerator;
import fr.marketsim.infrastructure.in.filter.TraceIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    protected TraceIdFilter traceIdFilter(UuidGenerator uuidGenerator) {
        return new TraceIdFilter(uuidGenerator);
    }

}
