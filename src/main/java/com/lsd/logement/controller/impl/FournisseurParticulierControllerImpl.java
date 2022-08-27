package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.FournisseurParticulierController;
import com.lsd.logement.dto.FournisseurParticulierDTO;
import com.lsd.logement.entity.stock.FournisseurParticulier;
import com.lsd.logement.mapper.FournisseurParticulierMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.FournisseurParticulierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/fournisseur/particulier")
@RestController
public class FournisseurParticulierControllerImpl implements FournisseurParticulierController {
    private final FournisseurParticulierService fournisseurParticulierService;
    private final FournisseurParticulierMapper fournisseurParticulierMapper;

    public FournisseurParticulierControllerImpl(FournisseurParticulierService fournisseurParticulierService, FournisseurParticulierMapper fournisseurParticulierMapper) {
        this.fournisseurParticulierService = fournisseurParticulierService;
        this.fournisseurParticulierMapper = fournisseurParticulierMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody FournisseurParticulierDTO fournisseurParticulierDTO) {
        try {
            FournisseurParticulier fournisseurParticulier = fournisseurParticulierMapper.asEntity(fournisseurParticulierDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(fournisseurParticulierMapper.asDTO(fournisseurParticulierService.save(fournisseurParticulier)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            FournisseurParticulier fournisseurParticulier = fournisseurParticulierService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(fournisseurParticulierMapper.asDTO(fournisseurParticulier))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            fournisseurParticulierService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "FournisseurParticulier supprimé avec succes")
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }

    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(fournisseurParticulierMapper.asDTOList(fournisseurParticulierService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<FournisseurParticulier> fournisseurParticulierPage = fournisseurParticulierService.findAll(pageable);
            List<FournisseurParticulierDTO> dtoList = fournisseurParticulierPage
                    .stream()
                    .map(fournisseurParticulierMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, fournisseurParticulierPage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody FournisseurParticulierDTO fournisseurParticulierDTO, @PathVariable("id") Integer id) {
        try {
            FournisseurParticulier fournisseurParticulier = fournisseurParticulierMapper.asEntity(fournisseurParticulierDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(fournisseurParticulierMapper.asDTO(fournisseurParticulierService.update(fournisseurParticulier, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}