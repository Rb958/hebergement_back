package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.LocatairesParticulierController;
import com.lsd.logement.dto.LocataireParticulierDTO;
import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.mapper.LocataireParticulierMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.LocataireParticulierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/locataire/particulier")
@RestController
public class LocataireParticulierControllerImpl implements LocatairesParticulierController {
    private final LocataireParticulierService locatairesService;
    private final LocataireParticulierMapper locatairesMapper;

    public LocataireParticulierControllerImpl(LocataireParticulierService locatairesService, LocataireParticulierMapper locatairesMapper) {
        this.locatairesService = locatairesService;
        this.locatairesMapper = locatairesMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody LocataireParticulierDTO locatairesDTO) {
        try {
            LocataireParticulier locataires = locatairesMapper.asEntity(locatairesDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(locatairesMapper.asDTO(locatairesService.save(locataires)))
            );
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            LocataireParticulier locataires = locatairesService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(locatairesMapper.asDTO(locataires))
            );
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            locatairesService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(200, "Successfully deleted")
            );
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(locatairesMapper.asDTOList(locatairesService.findAll()))
            );
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<LocataireParticulier> locatairesPage = locatairesService.findAll(pageable);
            List<LocataireParticulierDTO> dtoList = locatairesPage
                    .stream()
                    .map(locatairesMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, locatairesPage.getTotalElements()))
            );
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody LocataireParticulierDTO locatairesDTO, @PathVariable("id") Integer id) {
        try {
            LocataireParticulier locataires = locatairesMapper.asEntity(locatairesDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(locatairesMapper.asDTO(locatairesService.update(locataires, id)))
            );
        } catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}