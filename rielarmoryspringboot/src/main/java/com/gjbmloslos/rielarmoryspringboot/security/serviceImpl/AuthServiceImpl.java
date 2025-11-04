package com.gjbmloslos.rielarmoryspringboot.security.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.Role;
import com.gjbmloslos.rielarmoryspringboot.model.User;
import com.gjbmloslos.rielarmoryspringboot.repository.RoleRepository;
import com.gjbmloslos.rielarmoryspringboot.repository.UserRepository;
import com.gjbmloslos.rielarmoryspringboot.security.JwtUtil;
import com.gjbmloslos.rielarmoryspringboot.security.model.AuthRequest;
import com.gjbmloslos.rielarmoryspringboot.security.model.AuthResponse;
import com.gjbmloslos.rielarmoryspringboot.security.model.RegisterRequest;
import com.gjbmloslos.rielarmoryspringboot.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails user = userRepository.findByName(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Override
    public ResponseEntity<String> register(RegisterRequest request) {

        if (userRepository.findByName(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = User.builder()
                .name(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.getReferenceById(request.getRoleId()))
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
