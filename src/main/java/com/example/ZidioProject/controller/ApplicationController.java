package com.example.ZidioProject.controller;


import com.example.ZidioProject.dto.ApplicationRequest;
import com.example.ZidioProject.dto.ApplicationResponse;

import com.example.ZidioProject.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ApplicationResponse apply(@RequestBody ApplicationRequest request) {
        return applicationService.apply(request);
    }

    @GetMapping("/student/{email}")
    public List<ApplicationResponse> getByStudentEmail(@PathVariable("email") String studentEmail) {
        return applicationService.getApplicationsByStudent(studentEmail);
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationResponse> getByJobId(@PathVariable Long jobId) {
        return applicationService.getApplicationByJob(jobId);
    }
}
