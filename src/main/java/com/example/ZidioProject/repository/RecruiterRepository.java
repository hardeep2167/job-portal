package com.example.ZidioProject.repository;


import com.example.ZidioProject.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {
    Recruiter findByRecruiterEmail(String recruiterEmail);
}
