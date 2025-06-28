package fr.marketsim.application.config;

import fr.marketsim.application.config.properties.JwtProperties;
import fr.marketsim.application.security.JjwtTokenManager;
import fr.marketsim.application.security.JwtTokenManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtTokenManager jwtTokenManager(JwtProperties jwtProperties) {
        return new JjwtTokenManager(jwtProperties);
    }

}