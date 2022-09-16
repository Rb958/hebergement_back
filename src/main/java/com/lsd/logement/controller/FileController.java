package com.lsd.logement.controller;

import com.lsd.logement.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileController {
    ResponseEntity<ApiResponse<?>> uploadFile(@RequestParam("file") MultipartFile file);

    ResponseEntity<?> getFile(@PathVariable("filename") String filename);
}
