package com.anaparthi.path_tracker.auth;

import com.anaparthi.path_tracker.dto.LoginRequest;
import com.anaparthi.path_tracker.dto.RegisterRequest;
import com.anaparthi.path_tracker.dto.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AppUserDetailsService userDetailsService;
    private final AuthenticationManager authManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AppUserDetailsService userDetailsService,
                       AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authManager = authManager;
    }

    public JwtResponse register(RegisterRequest req) {

        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(
                null,
                req.getUsername(),
                passwordEncoder.encode(req.getPassword()),
                "USER"
        );

        userRepository.save(user);

        UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(ud);

        return new JwtResponse(token);
    }

    public JwtResponse login(LoginRequest req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        UserDetails ud = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtService.generateToken(ud);

        return new JwtResponse(token);
    }
}
