package fr.marketsim.application.service.mapper;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static fr.marketsim.application.utilities.ApplicationConstants.EMAIL_ALREADY_EXISTS;

public final class ExceptionHttpMapperService implements ExceptionHttpMapper {

    private static final Map<String, HttpStatus> BUSINESS_CODE_TO_STATUS = Map.of(
            EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT
    );

    @Override
    public HttpStatus getHttpStatusFromBusinessCode(@NonNull String businessCode) {
        return BUSINESS_CODE_TO_STATUS.getOrDefault(businessCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
