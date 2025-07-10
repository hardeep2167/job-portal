

package com.example.ZidioProject.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private Cloudinary cloudinary;

    public Map<String, String> upload(MultipartFile file) throws IOException {
        Map<String, String> result = new HashMap<>();

        // 1️⃣ Save to local directory
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String localFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = path.resolve(localFileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        String localUrl = "/files/" + localFileName;
        result.put("localUrl", localUrl);

        // 2️⃣ Upload to Cloudinary
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "public_id", UUID.randomUUID().toString()
                )
        );
        result.put("cloudinaryUrl", uploadResult.get("secure_url").toString());

        return result;
    }
}
