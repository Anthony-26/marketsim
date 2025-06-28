package fr.marketsim.domain.exception.business;

import static fr.marketsim.application.utilities.ApplicationConstants.INVALID_CREDENTIALS;

public final class InvalidCredentialsException extends BusinessException {

    public InvalidCredentialsException() {
        super("Invalid credentials", INVALID_CREDENTIALS);
    }

}
