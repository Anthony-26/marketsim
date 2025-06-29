package fr.marketsim.domain.exception.business;

import static fr.marketsim.application.utilities.ApplicationConstants.EMAIL_ALREADY_EXISTS;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException() {
        super("Email already exists.", EMAIL_ALREADY_EXISTS);
    }
}
