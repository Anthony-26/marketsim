package fr.marketsim.application.exception;

public class JwtExtractionException extends RuntimeException {

    public JwtExtractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtExtractionException(String message) {
        super(message);
    }

}
