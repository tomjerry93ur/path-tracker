package com.anaparthi.path_tracker.auth;

import com.anaparthi.path_tracker.dto.LoginRequest;
import com.anaparthi.path_tracker.dto.RegisterRequest;
import com.anaparthi.path_tracker.dto.JwtResponse;
import com.anaparthi.path_tracker.dto.RegisterResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public JwtResponse token(@RequestParam String username,
                                     @RequestParam String password) {

        return authService.login(LoginRequest.builder()
                        .username(username)
                        .password(password)
                .build());

    }
}