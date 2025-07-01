package com.example.ZidioProject.controller;


import com.example.ZidioProject.dto.StudentDto;
import com.example.ZidioProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;  // Fixed StudentService capitalization

    @PostMapping("/profile")  // Fixed @Postmapping -> @PostMapping
    public ResponseEntity<String> save(@RequestBody StudentDto dto) {  // Fixed pubic -> public, ReponseEntity -> ResponseEntity
        return ResponseEntity.ok(studentService.createOrUpdate(dto));  // Fixed reurn -> return
    }

    @GetMapping("/profile/{email}")  // Added missing /
    public ResponseEntity<StudentDto> get(@PathVariable String email) {  // Fixed ReponseEntity, Sting -> String
        return ResponseEntity.ok(studentService.getProfile(email));  // Fixed studenService
    }
}