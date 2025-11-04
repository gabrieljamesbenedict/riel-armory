package com.gjbmloslos.rielarmoryspringboot.security.service;

import com.gjbmloslos.rielarmoryspringboot.security.model.AuthRequest;
import com.gjbmloslos.rielarmoryspringboot.security.model.AuthResponse;
import com.gjbmloslos.rielarmoryspringboot.security.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponse> login(AuthRequest request);
    ResponseEntity<String> register(RegisterRequest request);
}
