package com.anaparthi.path_tracker.auth;

import com.anaparthi.path_tracker.dto.LoginRequest;
import com.anaparthi.path_tracker.dto.RegisterRequest;
import com.anaparthi.path_tracker.dto.JwtResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public JwtResponse register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}