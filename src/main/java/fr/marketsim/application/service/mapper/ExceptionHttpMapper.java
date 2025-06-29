package fr.marketsim.application.service.mapper;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public interface ExceptionHttpMapper {

    HttpStatus getHttpStatusFromBusinessCode(@NonNull final String businessCode);

}
