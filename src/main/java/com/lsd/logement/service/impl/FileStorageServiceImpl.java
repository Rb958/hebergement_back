package com.lsd.logement.service.impl;

import com.lsd.logement.dao.MediaRepository;
import com.lsd.logement.entity.Media;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("uploads");

    private final MediaRepository repository;

    public FileStorageServiceImpl(MediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void init() {
        try {
            if (Files.notExists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new GeneralBaseException(500, "Could not initialize folder for upload!");
        }
    }
    @Override
    public Media save(MultipartFile file) {
        try {
            Path filePath = this.root.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath);
            Media media = new Media();
            media.setMediaName(file.getOriginalFilename());
            media.setMediaType(file.getContentType());
            media.setPath(filePath.toString());
            media.setRef(UUID.randomUUID().toString());
            return repository.save(media);
        } catch (Exception e) {
            throw new GeneralBaseException(500, "Could not store the file. Error: " + e.getMessage());
        }
    }
    @Override
    public Resource load(String ref) {
        try {
            Optional<Media> optional = repository.findByRef(ref);
            if (!optional.isPresent()){
                throw new GeneralBaseException(404, "The file not found");
            }
            Path file = root.resolve(optional.get().getMediaName());
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new GeneralBaseException(500, "Could not read the file!");
            }
        } catch (IOException e) {
            throw new GeneralBaseException(500, "Error: " + e.getMessage());
        }
    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new GeneralBaseException(500, "Could not load the files!");
        }
    }
}
