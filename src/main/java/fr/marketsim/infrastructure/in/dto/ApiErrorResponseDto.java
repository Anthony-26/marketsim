package fr.marketsim.infrastructure.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiErrorResponseDto extends ApiResponseDto {

    private final Integer statusCode;
    private final String businessCode;
    private final String path;

    public static ApiErrorResponseDto of(
            @NonNull final Integer statusCode, @NonNull String businessCode, @NonNull String path
    ) {
        return new ApiErrorResponseDto(statusCode, businessCode, path);
    }

    private ApiErrorResponseDto(
            final Integer statusCode, final String businessCode, final String path
    ) {
        super();
        this.statusCode = statusCode;
        this.businessCode = businessCode;
        this.path = path;
    }

}
