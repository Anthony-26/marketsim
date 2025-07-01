package fr.marketsim.infrastructure.out.database.adapter;

import fr.marketsim.application.port.out.UserPersistencePort;
import fr.marketsim.application.service.mapper.UserMapper;
import fr.marketsim.domain.model.User;
import fr.marketsim.infrastructure.out.database.entity.user.UserEntity;
import fr.marketsim.infrastructure.out.database.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPersistencePort {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User save(@NonNull User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        User convertedUser = userMapper.toDomain(savedUserEntity);
        log.info("User successfully saved : {}.", convertedUser.getEmail());
        return convertedUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> optUserEntity = userRepository.findByEmail(email);
        return optUserEntity.map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
