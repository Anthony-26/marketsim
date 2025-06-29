package fr.marketsim.application.exception;

public class JwtSecretPropertyException extends RuntimeException {

    public JwtSecretPropertyException(String message) {
        super(message);
    }

    public JwtSecretPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

}
