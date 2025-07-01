package com.example.ZidioProject.controller;

import com.example.ZidioProject.dto.JobRequest;
import com.example.ZidioProject.dto.RecruiterRequest;
import com.example.ZidioProject.entity.Job;
import com.example.ZidioProject.entity.Recruiter;
import com.example.ZidioProject.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/profile")
    public ResponseEntity<Recruiter> saveProfile(@RequestBody RecruiterRequest request) {
        return ResponseEntity.ok(recruiterService.saveProfile(request));
    }


    @PostMapping("/job")
    public ResponseEntity<Job> postJobs(@RequestBody JobRequest request) {
        return ResponseEntity.ok(recruiterService.postJob(request));
    }


    @GetMapping("/jobs/{email}")
    public ResponseEntity<List<Job>> getPostedJob(@PathVariable String email) {
        return ResponseEntity.ok(recruiterService.getPostedJob(email));
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(recruiterService.getAllJobs());
    }
}
