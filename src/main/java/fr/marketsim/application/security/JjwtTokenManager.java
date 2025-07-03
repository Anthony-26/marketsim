package fr.marketsim.application.security;

import fr.marketsim.application.exception.JwtExtractionException;
import fr.marketsim.application.exception.JwtSecretPropertyException;
import fr.marketsim.application.exception.JwtSigningKeyException;
import fr.marketsim.application.config.properties.JwtProperties;
import fr.marketsim.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public final class JjwtTokenManager implements JwtTokenManager {

    private final JwtProperties jwtProperties;

    private volatile Key signingKey;
    private volatile JwtParser jwtParser;

    @Override
    public String generateToken(@NonNull final User user) {
        log.debug("Generating JWT token for user '{}' ...", user.getPublicId());

        return Jwts.builder()
                .subject(user.getPublicId().toString())
                .issuer(jwtProperties.issuer())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.expirationMs()))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public boolean validateToken(@NonNull final String token) {
        try {
            retrieveSignedClaimsFromToken(token);
            return true;
        } catch (JwtException e) {
            log.warn("Invalid JWT Token.", e);
            return false;
        }
    }

    @Override
    public String extractSubject(@NonNull final String token) {
        try {
            Claims claims = retrieveSignedClaimsFromToken(token);
            return claims.getSubject();
        } catch (JwtException e) {
            throw new JwtExtractionException("Failed to extract 'Subject' from token.", e);
        }
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

                        signingKey = Keys.hmacShaKeyFor(keyBytes);
                        log.debug("Signing key successfully initialized.");
                    } catch (WeakKeyException e) {
                        throw new JwtSigningKeyException("JWT secret key is too weak for HMAC-SHA256.", e);
                    }
                }
            }
        }
        return signingKey;
    }

    private JwtParser getJwtParser() {
        if (jwtParser == null) {
            synchronized (this) {
                if (jwtParser == null) {
                    jwtParser = Jwts.parser()
                            .verifyWith((SecretKey) getSigningKey())
                            .requireIssuer(jwtProperties.issuer())
                            .build();
                }
            }
        }
        return jwtParser;
    }

    private Claims retrieveSignedClaimsFromToken(final String token) throws JwtException {
        return getJwtParser()
                .parseSignedClaims(token)
                .getPayload();
    }

}
