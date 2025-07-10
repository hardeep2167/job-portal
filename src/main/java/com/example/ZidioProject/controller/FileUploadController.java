package com.example.ZidioProject.controller;

import com.example.ZidioProject.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;



    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> urls = fileUploadService.upload(file);
        return ResponseEntity.ok(urls);
    }


    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get("upload/resumes").resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)  // <-- important for browser
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
