package com.example.ZidioProject.service;






import com.example.ZidioProject.dto.JobRequest;
import com.example.ZidioProject.entity.Job;
import com.example.ZidioProject.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public String jobPost(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCompanyName(request.getCompanyName());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setDuration(request.getDuration());
        job.setSkills(request.getSkills());
        job.setPostedBy(request.getPostedBy());

        jobRepository.save(job);

        return "Job post got saved";
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> searchByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Job> searchByCompanyName(String companyName) {
        return jobRepository.findByCompanyName(companyName);
    }
}
