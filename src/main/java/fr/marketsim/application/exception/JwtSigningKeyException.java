package fr.marketsim.application.exception;

public class JwtSigningKeyException extends RuntimeException {

    public JwtSigningKeyException(String message, Throwable cause) {
        super(message, cause);
    }

}
