package fr.marketsim.application.service.mapper;

import fr.marketsim.domain.model.Account;
import fr.marketsim.domain.model.User;
import fr.marketsim.infrastructure.out.database.entity.user.AccountEntity;

public class AccountMapper {

    public Account toDomain(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .publicId(entity.getPublicId())
                .balance(entity.getBalance())
                .build();
    }

    public AccountEntity toEntity(Account domain, User user) {
        return AccountEntity.builder()
                .id(domain.getId())
                .publicId(domain.getPublicId())
                .balance(domain.getBalance())
                .user(null)
                .build();
    }

}
