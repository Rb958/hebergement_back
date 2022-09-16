package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.FileController;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.FileStorageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/file")
public class FileControllerImpl implements FileController {

    private final FileStorageService fileStorageService;

    public FileControllerImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<?>> uploadFile(MultipartFile file) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(fileStorageService.save(file)));
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{filename}")
    public ResponseEntity<?> getFile(String filename) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=Casier-report.pdf");
            Resource resource = fileStorageService.load(filename);
            String mime = Files.probeContentType(resource.getFile().toPath());
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType(mime))
                    .body(new InputStreamResource(resource.getInputStream()));
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}
