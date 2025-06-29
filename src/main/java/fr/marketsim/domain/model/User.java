package fr.marketsim.domain.model;

import fr.marketsim.application.service.utilities.UuidGenerator;
import lombok.*;

import java.util.UUID;

import static fr.marketsim.application.utilities.ApplicationConstants.INITIAL_BALANCE;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public final class User {

    private Long id;
    private UUID publicId;
    private String email;
    private String passwordHash;
    private Role role;
    private Account account;

    public static User register(@NonNull String email, @NonNull String passwordHash,
                                @NonNull UuidGenerator uuidGenerator) {
        Account account = Account.builder()
                .publicId(uuidGenerator.generateUuid())
                .balance(INITIAL_BALANCE)
                .build();

        return User.builder()
                .publicId(uuidGenerator.generateUuid())
                .email(email)
                .passwordHash(passwordHash)
                .role(Role.USER)
                .account(account)
                .build();
    }

    public static User fromPersistence(
            @NonNull Long id,
            @NonNull UUID publicId,
            @NonNull String email,
            @NonNull String passwordHash,
            @NonNull Role role,
            @NonNull Account account
    ) {
        return User.builder()
                .id(id)
                .publicId(publicId)
                .email(email)
                .passwordHash(passwordHash)
                .role(role)
                .account(account)
                .build();
    }


}
