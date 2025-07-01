package fr.marketsim.application.service.workflow;

import fr.marketsim.application.port.in.UserOrchestrator;
import fr.marketsim.application.port.out.UserPersistencePort;
import fr.marketsim.application.security.JwtTokenManager;
import fr.marketsim.application.service.utilities.UuidGenerator;
import fr.marketsim.domain.exception.business.EmailAlreadyExistsException;
import fr.marketsim.domain.exception.business.EmailDoesNotExistException;
import fr.marketsim.domain.exception.business.InvalidCredentialsException;
import fr.marketsim.domain.model.User;
import fr.marketsim.infrastructure.in.dto.LoginResponseDto;
import fr.marketsim.infrastructure.in.dto.RegisterResponseDto;
import fr.marketsim.infrastructure.in.dto.UserRequestDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Validated
public class DefaultUserOrchestrator implements UserOrchestrator {

    private final UserPersistencePort userPersistencePort;
    private final UuidGenerator randomUuidGenerator;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager jwtTokenManager;

    @Override
    @Transactional
    public RegisterResponseDto registerUser(@Valid @NonNull UserRequestDto userRequestDto) {
        String email = cleanEmail(userRequestDto.getEmail());
        log.info("Starting registration.");

        Optional.of(email)
                .filter(e -> !userPersistencePort.existsByEmail(email))
                .orElseThrow(EmailAlreadyExistsException::new);

        User user = User.register(
                email, passwordEncoder.encode(userRequestDto.getPassword()), randomUuidGenerator
        );
        userPersistencePort.save(user);
        log.info("Registered user '{}'.", user.getEmail());

        return RegisterResponseDto.of(jwtTokenManager.generateToken(user));
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto loginUser(UserRequestDto userRequestDto) {
        String email = cleanEmail(userRequestDto.getEmail());
        log.info("Authentication attempt for email '{}'.", email);

        User user = userPersistencePort.findByEmail(email).orElseThrow(EmailDoesNotExistException::new);

        if (!passwordEncoder.matches(userRequestDto.getPassword(), user.getPasswordHash()))
            throw new InvalidCredentialsException();

        return LoginResponseDto.of(jwtTokenManager.generateToken(user));
    }

    private String cleanEmail(String email) {
        return email.trim().toLowerCase().replaceAll("[\\r\\n]", "");
    }

}
