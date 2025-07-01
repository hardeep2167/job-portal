package com.example.ZidioProject.dto;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationResponse {
    private Long id;
    private String studentEmail;
    private Long jobId;
    private String status;
}
