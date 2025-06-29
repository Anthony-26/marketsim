package fr.marketsim.application.security;

import fr.marketsim.application.exception.JwtSecretPropertyException;
import fr.marketsim.application.exception.JwtSigningKeyException;
import fr.marketsim.application.config.properties.JwtProperties;
import fr.marketsim.domain.model.Role;
import fr.marketsim.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public final class JjwtTokenManager implements JwtTokenManager {

    private final JwtProperties jwtProperties;

    private volatile Key signingKey;

    @Override
    public String generateToken(User user) {
        log.debug("Generating JWT token for user '{}' ...", user.getPublicId());
        Map<String, Object> claims = buildClaims(user);

        return Jwts.builder()
                .subject(user.getPublicId().toString())
                .claims(claims)
                .issuer(jwtProperties.issuer())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.expirationMs()))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token, User user) {
        log.debug("Validating JWT token for user '{}' ...", user.getPublicId());
        String tokenSubject = extractSubject(token);
        return tokenSubject.equals(user.getPublicId().toString());
    }

    @Override
    public String extractSubject(String token) {
        log.debug("Extracting subject from token ...");
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Role extractRole(String token) {
        log.debug("Extracting role from token ...");
        return extractClaim(token, claims -> claims.get("role", Role.class));
    }

    private Map<String, Object> buildClaims(User user) {
        return Map.of(
                "role", user.getRole()
        );
    }

    private Key getSigningKey() {
        if (signingKey == null) {
            synchronized (this) {
                if (signingKey == null) {
                    try {
                        log.debug("Initializing signing key ...");
                        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());

                        Optional.of(keyBytes.length)
                                .filter(length -> length >= 32)
                                .orElseThrow(() -> new JwtSecretPropertyException(String.format(
                                        "JWT secret must be at least 256 bits (32 bytes). Currently '%s' bytes'.",
                                        keyBytes.length)));

                        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
                        log.debug("Signing key successfully initialized.");
                    } catch (WeakKeyException e) {
                        throw new JwtSigningKeyException("JWT secret key is too weak for HMAC-SHA256.", e);
                    }
                }
            }
        }
        return this.signingKey;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) getSigningKey())
                    .requireIssuer(jwtProperties.issuer())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claimsResolver.apply(claims);
        } catch (JwtException e) {
            throw new JwtException("Failed to extract claim from token.", e);
        } catch (IllegalArgumentException e) {
            throw new JwtSecretPropertyException("JWT secret is not valid Base64 format.", e);
        }
    }

}
