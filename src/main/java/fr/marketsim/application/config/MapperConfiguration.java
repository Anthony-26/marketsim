package fr.marketsim.application.config;

import fr.marketsim.application.service.mapper.AccountMapper;
import fr.marketsim.application.service.mapper.ExceptionHttpMapper;
import fr.marketsim.application.service.mapper.ExceptionHttpMapperService;
import fr.marketsim.application.service.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    protected UserMapper userMapper(AccountMapper accountMapper) {
        return new UserMapper(accountMapper);
    }

    @Bean
    protected AccountMapper accountMapper() {
        return new AccountMapper();
    }

    @Bean
    protected ExceptionHttpMapper exceptionHttpMapperService() {
        return new ExceptionHttpMapperService();
    }

}
