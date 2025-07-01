//package com.example.ZidioProject.service;
//
//import com.example.ZidioProject.dto.RecruiterRequest;
//import com.example.ZidioProject.entity.Recruiter;
//import com.example.ZidioProject.repository.RecruiterRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class RecruiterService {
//
//    @Autowired
//    private RecruiterRepository recruiterRepository;
//
//    public RecruiterRequest saveRecruiter(RecruiterRequest request) {
//        Recruiter recruiter = new Recruiter();
//        recruiter.setRecruiterName(request.getRecruiterName());
//        recruiter.setCompanyName(request.getCompanyName());
//        recruiter.setRecruiterEmail(request.getRecruiterEmail());
//        recruiter.setDesignation(request.getDesignation());
//
//        Recruiter saved = recruiterRepository.save(recruiter);
//
//        RecruiterRequest response = new RecruiterRequest();
//        BeanUtils.copyProperties(saved, response);
//        return response;
//    }
//
//    public List<RecruiterRequest> getAllRecruiters() {
//        return recruiterRepository.findAll().stream().map(r -> {
//            RecruiterRequest dto = new RecruiterRequest();
//            BeanUtils.copyProperties(r, dto);
//            return dto;
//        }).collect(Collectors.toList());
//    }
//
//    public RecruiterRequest getByRecruiterEmail(String recruiterEmail) {
//        Recruiter recruiter = recruiterRepository.findByRecruiterEmail(recruiterEmail);
//        if (recruiter == null) throw new RuntimeException("Recruiter not found");
//
//        RecruiterRequest dto = new RecruiterRequest();
//        BeanUtils.copyProperties(recruiter, dto);
//        return dto;
//    }
//}
//-----------------------------------------------


package com.example.ZidioProject.service;

import com.example.ZidioProject.dto.JobRequest;
import com.example.ZidioProject.dto.RecruiterRequest;
import com.example.ZidioProject.entity.Job;
import com.example.ZidioProject.entity.Recruiter;
import com.example.ZidioProject.repository.JobRepository;
import com.example.ZidioProject.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private JobRepository jobRepository;

    public Recruiter saveProfile(RecruiterRequest request) {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setRecruiterName(request.getRecruiterName());
        recruiter.setRecruiterEmail(request.getRecruiterEmail());
        recruiter.setDesignation(request.getDesignation());

        Recruiter saved = recruiterRepository.save(recruiter);
        System.out.println("Recruiter saved: " + saved);  // Add log
        return saved;
    }

    public Job postJob(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setJobType(request.getJobType());
        job.setLocation(request.getLocation());
        job.setPostedBy(request.getPostedBy());

        return jobRepository.save(job);
    }

    public List<Job> getPostedJob(String recruiterEmail) {
        return jobRepository.findByPostedBy(recruiterEmail);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}

