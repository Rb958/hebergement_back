package com.lsd.logement.service;

import com.lsd.logement.entity.Media;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init();
    public Media save(MultipartFile file);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}