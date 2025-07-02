package fr.marketsim.application.config;

import fr.marketsim.application.port.out.UserPersistencePort;
import fr.marketsim.application.security.JwtTokenManager;
import fr.marketsim.application.service.utilities.UuidGenerator;
import fr.marketsim.application.service.workflow.DefaultUserOrchestrator;
import fr.marketsim.application.port.in.UserOrchestrator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WorkflowConfiguration {

    @Bean
    public UserOrchestrator userOrchestratorService(UserPersistencePort userPersistencePort,
                                                    UuidGenerator uuidGenerator,
                                                    PasswordEncoder passwordEncoder,
                                                    JwtTokenManager jwtTokenManager) {
        return new DefaultUserOrchestrator(userPersistencePort, uuidGenerator, passwordEncoder, jwtTokenManager);
    }

}
