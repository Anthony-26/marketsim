package fr.marketsim.infrastructure.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class LoginResponseDto extends ApiResponseDto {

    private final String accessToken;

    private LoginResponseDto(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public static LoginResponseDto of(String accessToken) {
        return new LoginResponseDto(accessToken);
    }

}