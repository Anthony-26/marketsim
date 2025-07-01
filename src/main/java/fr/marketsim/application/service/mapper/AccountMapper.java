package fr.marketsim.application.service.mapper;

import fr.marketsim.domain.model.Account;
import fr.marketsim.infrastructure.out.database.entity.user.AccountEntity;

public class AccountMapper {

    public Account toDomain(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .publicId(entity.getPublicId())
                .balance(entity.getBalance())
                .build();
    }

}
