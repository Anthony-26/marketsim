package fr.marketsim.application.service.mapper;

import fr.marketsim.domain.model.User;
import fr.marketsim.infrastructure.out.database.entity.user.AccountEntity;
import fr.marketsim.infrastructure.out.database.entity.user.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserMapper {

    private final AccountMapper accountMapper;

    public User toDomain(@NonNull UserEntity entity) {
        Long id = entity.getId();
        Optional.ofNullable(entity.getAccount())
                .orElseThrow(() ->
                        new DataIntegrityViolationException(
                                String.format("User with id '%s' has no associated Account.", id)
                        )
                );

        log.debug("Converting UserEntity with id '{}' to User Domain Object.", id);

        return User.fromPersistence(
                entity.getId(),
                entity.getPublicId(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getRole(),
                accountMapper.toDomain(entity.getAccount())
        );
    }

    public UserEntity toEntity(@NonNull User domain) {
        UserEntity userEntity = UserEntity.builder()
                .publicId(domain.getPublicId())
                .email(domain.getEmail())
                .passwordHash(domain.getPasswordHash())
                .account(null)
                .role(domain.getRole())
                .build();

        AccountEntity accountEntity = AccountEntity.builder()
                .publicId(domain.getAccount().getPublicId())
                .balance(domain.getAccount().getBalance())
                .user(userEntity)
                .build();

        userEntity.setAccount(accountEntity);

        return userEntity;
    }

}
