package com.example.ZidioProject.service;


import com.example.ZidioProject.dto.ApplicationRequest;
import com.example.ZidioProject.dto.ApplicationResponse;
import com.example.ZidioProject.entity.Application;
import com.example.ZidioProject.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationResponse apply(ApplicationRequest request) {
        applicationRepository.findByStudentEmailAndJobId(request.getStudentEmail(), request.getJobId())
                .ifPresent(a -> { throw new RuntimeException("Already applied"); });

        Application app = Application.builder()
                .studentEmail(request.getStudentEmail())
                .jobId(request.getJobId())
                .status("applied")
                .build();

        app = applicationRepository.save(app);
        return mapToResponse(app);
    }

    public List<ApplicationResponse> getApplicationsByStudent(String studentEmail) {
        return applicationRepository.findByStudentEmail(studentEmail)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ApplicationResponse> getApplicationByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ApplicationResponse mapToResponse(Application app) {
        return new ApplicationResponse(
                app.getId(),
                app.getStudentEmail(),
                app.getJobId(),
                app.getStatus()
        );
    }
}
