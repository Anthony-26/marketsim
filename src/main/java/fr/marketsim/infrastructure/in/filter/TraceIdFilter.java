package fr.marketsim.infrastructure.in.filter;

import fr.marketsim.application.service.utilities.UuidGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static fr.marketsim.application.utilities.ApplicationConstants.TRACE_ID_HEADER_KEY;
import static fr.marketsim.application.utilities.ApplicationConstants.TRACE_ID_KEY;

@RequiredArgsConstructor
@Slf4j
public class TraceIdFilter extends OncePerRequestFilter {

    private final UuidGenerator uuidGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String traceId = Optional.ofNullable(request.getHeader(TRACE_ID_HEADER_KEY)).
                orElse(uuidGenerator.generateUuid().toString());

        MDC.put(TRACE_ID_KEY, traceId);
        response.addHeader(TRACE_ID_HEADER_KEY, traceId);

        log.debug("Request received and {} initialized.", TRACE_ID_KEY);

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID_KEY);
        }
    }
}
