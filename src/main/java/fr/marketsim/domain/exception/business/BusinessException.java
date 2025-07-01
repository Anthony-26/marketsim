package fr.marketsim.domain.exception.business;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final String businessCode;

    protected BusinessException(@NonNull String message, @NonNull String businessCode) {
        super(message);
        this.businessCode = businessCode;
    }

}
