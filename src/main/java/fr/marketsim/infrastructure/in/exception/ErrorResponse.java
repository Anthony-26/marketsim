package fr.marketsim.infrastructure.in.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ErrorResponse {

    @Builder.Default
    private final Instant timestamp = Instant.now();
    private final int httpStatusCode;
    private final String businessCode;
    private final String message;
    private final String path;
    private final String traceId;

}
