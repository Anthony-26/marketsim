package fr.marketsim.application.exception;

public class JwtExtractionException extends RuntimeException {
    public JwtExtractionException(String message) {
        super(message);
    }
}
