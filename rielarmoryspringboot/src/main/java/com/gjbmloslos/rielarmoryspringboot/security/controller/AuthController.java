package com.gjbmloslos.rielarmoryspringboot.security.controller;

import com.gjbmloslos.rielarmoryspringboot.security.JwtUtil;
import com.gjbmloslos.rielarmoryspringboot.security.model.AuthRequest;
import com.gjbmloslos.rielarmoryspringboot.security.model.RegisterRequest;
import com.gjbmloslos.rielarmoryspringboot.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

}
