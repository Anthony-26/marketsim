package fr.marketsim.infrastructure.in.exception;

import fr.marketsim.application.service.mapper.ExceptionHttpMapper;
import fr.marketsim.domain.exception.business.BusinessException;
import fr.marketsim.infrastructure.in.dto.ApiErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static fr.marketsim.application.utilities.ApplicationConstants.TRACE_ID_KEY;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final ExceptionHttpMapper exceptionMapperService;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponseDto> handleBusinessException(
            BusinessException ex, HttpServletRequest request) {

        String businessCode = ex.getBusinessCode();
        int httpStatusCode = exceptionMapperService.getHttpStatusFromBusinessCode(businessCode).value();
        String traceId = MDC.get(TRACE_ID_KEY);

        log.warn("TraceId : {} - Business exception occured. Message '{}', Business Code '{}' and HttpStatusCode '{}'",
                traceId, ex.getMessage(), businessCode, httpStatusCode);
        return ResponseEntity.status(httpStatusCode).body(null);

    }

}
