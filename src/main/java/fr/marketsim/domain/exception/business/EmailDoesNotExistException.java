package fr.marketsim.domain.exception.business;

import static fr.marketsim.application.utilities.ApplicationConstants.EMAIL_DOES_NOT_EXIST;

public class EmailDoesNotExistException extends BusinessException {

    public EmailDoesNotExistException() {
        super("User has to register first before logging in.", EMAIL_DOES_NOT_EXIST);
    }

}
