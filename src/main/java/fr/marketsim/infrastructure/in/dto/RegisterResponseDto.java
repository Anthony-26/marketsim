package fr.marketsim.infrastructure.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class RegisterResponseDto extends ApiResponseDto {

    private final String accessToken;

    private RegisterResponseDto(final String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public static RegisterResponseDto of(@NonNull final String accessToken) {
        return new RegisterResponseDto(accessToken);
    }

}
