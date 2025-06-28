package fr.marketsim.infrastructure.in.dto;

import lombok.Getter;
import org.slf4j.MDC;

import java.time.Instant;

import static fr.marketsim.application.utilities.ApplicationConstants.TRACE_ID_KEY;

@Getter
abstract sealed class ApiResponseDto permits ApiErrorResponseDto, RegisterResponseDto, LoginResponseDto {

    private final Instant timestamp;
    private final String traceId;

    ApiResponseDto() {
        this.timestamp = Instant.now();
        this.traceId = MDC.get(TRACE_ID_KEY);
    }

}
