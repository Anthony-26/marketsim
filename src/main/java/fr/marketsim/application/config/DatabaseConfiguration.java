package fr.marketsim.application.config;

import fr.marketsim.application.port.out.UserPersistencePort;
import fr.marketsim.application.service.mapper.UserMapper;
import fr.marketsim.infrastructure.out.database.adapter.UserRepositoryAdapter;
import fr.marketsim.infrastructure.out.database.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public UserPersistencePort userRepositoryAdapter(UserMapper userMapper,
                                                     UserRepository userRepository) {
        return new UserRepositoryAdapter(userMapper, userRepository);
    }

}
