package com.example.ZidioProject.service;

import com.example.ZidioProject.dto.LoginRequest;
import com.example.ZidioProject.dto.RegisterRequest;
import com.example.ZidioProject.entity.User;
import com.example.ZidioProject.repository.UserRepository;
import com.example.ZidioProject.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public String register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.email);
        user.setPassword(new BCryptPasswordEncoder().encode(request.password));
        user.setRole(request.role);
        userRepository.save(user);
        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("User not Found"));

        if (!new BCryptPasswordEncoder().matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
