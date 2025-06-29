package fr.marketsim.infrastructure.in.controller;


import fr.marketsim.application.port.in.UserOrchestrator;
import fr.marketsim.infrastructure.in.dto.LoginResponseDto;
import fr.marketsim.infrastructure.in.dto.RegisterResponseDto;
import fr.marketsim.infrastructure.in.dto.UserRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserOrchestrator userOrchestrator;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(
            @Valid @RequestBody UserRequestDto userRequestDto) {
        RegisterResponseDto registerResponse = userOrchestrator.registerUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        userOrchestrator.loginUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(LoginResponseDto.of("Login successful"));
    }

}
