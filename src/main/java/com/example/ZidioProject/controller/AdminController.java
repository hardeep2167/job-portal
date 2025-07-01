package com.example.ZidioProject.controller;

import com.example.ZidioProject.dto.UserInfoResponse;
import com.example.ZidioProject.dto.UserStatusUpdateRequest;

import com.example.ZidioProject.entity.UserInfo;
import com.example.ZidioProject.repository.UserInfoRepository;
import com.example.ZidioProject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserInfoRepository userInfoRepository;


    @PutMapping("/user/status")
    public ResponseEntity<UserInfoResponse> updateUserStatus(@RequestBody UserStatusUpdateRequest request) {
        UserInfoResponse response = adminService.updateUserStatus(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/users")
    public List<UserInfoResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{role}")
    public List<UserInfoResponse> getUsersByRole(@PathVariable String role) {
        return adminService.getUsersByRole(role);
    }

    @PostMapping("/add-user")
    public UserInfoResponse addUser(@RequestBody UserInfo userInfo) {
        UserInfo saved = userInfoRepository.save(userInfo);
        return adminService.toResponse(saved);
    }
    }

