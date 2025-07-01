package com.example.ZidioProject.controller;


import com.example.ZidioProject.dto.LoginRequest;
import com.example.ZidioProject.dto.RegisterRequest;
import com.example.ZidioProject.security.JWTUtil;
import com.example.ZidioProject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token); // TOKEN shown in Postman
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        boolean isValid = jwtUtil.validateToken(token);
        return isValid ? ResponseEntity.ok("Valid Token") : ResponseEntity.status(401).body("Invalid Token");
    }

}
