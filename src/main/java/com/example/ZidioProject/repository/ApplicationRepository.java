package com.example.ZidioProject.repository;


import com.example.ZidioProject.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByStudentEmailAndJobId(String studentEmail, Long jobId);

    List<Application> findByStudentEmail(String studentEmail);

    List<Application> findByJobId(Long jobId);
}
