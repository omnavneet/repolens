package com.repolens.backend.controller;

import com.repolens.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    // Body: { "name": "...", "email": "...", "password": "..." }
    // Returns: { "token": "...", "user": { "id", "name", "email" } }
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> body) {
        String name     = body.get("name");
        String email    = body.get("email");
        String password = body.get("password");

        if (name == null || email == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "name, email and password are required"));
        }

        Map<String, Object> response = authService.register(name, email, password);
        return ResponseEntity.ok(response);
    }

    // POST /api/auth/login
    // Body: { "email": "...", "password": "..." }
    // Returns: { "token": "...", "user": { "id", "name", "email" } }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String email    = body.get("email");
        String password = body.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "email and password are required"));
        }

        Map<String, Object> response = authService.login(email, password);
        return ResponseEntity.ok(response);
    }
}
