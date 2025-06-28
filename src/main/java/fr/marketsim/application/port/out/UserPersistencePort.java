package fr.marketsim.application.port.out;

import fr.marketsim.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
