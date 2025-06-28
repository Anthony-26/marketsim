package fr.marketsim.application.config;

import fr.marketsim.application.service.utilities.RandomUuidGenerator;
import fr.marketsim.application.service.utilities.UuidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilitiesConfiguration {

    @Bean
    public UuidGenerator randomUuidGenerator() {
        return new RandomUuidGenerator();
    }

}
