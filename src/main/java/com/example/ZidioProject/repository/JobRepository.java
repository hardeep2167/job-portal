package com.example.ZidioProject.repository;





import com.example.ZidioProject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByCompanyName(String companyName);
    List<Job> findByPostedBy(String postedBy);
}
