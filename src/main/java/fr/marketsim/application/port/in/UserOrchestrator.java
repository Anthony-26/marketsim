package fr.marketsim.application.port.in;

import fr.marketsim.infrastructure.in.dto.LoginResponseDto;
import fr.marketsim.infrastructure.in.dto.RegisterResponseDto;
import fr.marketsim.infrastructure.in.dto.UserRequestDto;
import jakarta.validation.Valid;

public interface UserOrchestrator {

    RegisterResponseDto registerUser(@Valid UserRequestDto userRequestDto);

    LoginResponseDto loginUser(@Valid UserRequestDto userRequestDto);

}
