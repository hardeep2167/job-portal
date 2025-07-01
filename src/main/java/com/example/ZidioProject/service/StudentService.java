package com.example.ZidioProject.service;


import com.example.ZidioProject.dto.StudentDto;
import com.example.ZidioProject.entity.Student;
import com.example.ZidioProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String createOrUpdate(StudentDto dto) {
        Student student = studentRepository.findByEmail(dto.email).orElse(new Student());  // Fixed Sudent -> Student
        student.setName(dto.name);
        student.setEmail(dto.email);  // Fixed stdent -> student
        student.setCourse(dto.course);
        student.setUniversity(dto.university);
        student.setResumeUrl(dto.resumeUrl);
        studentRepository.save(student);
        return "Student Profile got saved";
    }

    public StudentDto getProfile(String email) {  // Fixed SudentDto -> StudentDto
        Student s = studentRepository.findByEmail(email).orElseThrow();  // Fixed sudenRepository
        StudentDto dto = new StudentDto();
        dto.name = s.getName();
        dto.email = s.getEmail();
        dto.course = s.getCourse();
        dto.university = s.getUniversity();
        dto.resumeUrl = s.getResumeUrl();
        return dto;  // Fixed reurn -> return
    }
}
