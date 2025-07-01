package fr.marketsim.application.security;

import fr.marketsim.domain.model.Role;
import fr.marketsim.domain.model.User;

public interface JwtTokenManager {

    String generateToken(User user);

    boolean validateToken(String token, User user);

    String extractSubject(String token);

    Role extractRole(String token);

}
