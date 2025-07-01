package com.example.ZidioProject.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
    private String title;
    private String description;
    private String companyName;
    private String location;
    private String jobType;
    private String duration;
    private String skills;
    private String postedBy;
}
