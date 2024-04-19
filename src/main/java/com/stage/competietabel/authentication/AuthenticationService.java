package com.stage.competietabel.authentication;


import com.stage.competietabel.authentication.AuthenticationResponse;
import com.stage.competietabel.authentication.config.JwtService;
import com.stage.competietabel.authentication.AuthenticationRequest;
import com.stage.competietabel.authentication.RegisterRequest;
import com.stage.competietabel.repository.model.user.Role;
import com.stage.competietabel.repository.model.user.User;
import com.stage.competietabel.repository.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .firstname(request.firstname())
            .lastname(request.lastname())
            .email(request.email())
            .password(passwordEncoder.encode((request.password())))
            .role(Role.USER)
            .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );
        var user = repository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
