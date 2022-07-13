package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.LigneCommandeController;
import com.lsd.logement.dto.LigneCommandeDTO;
import com.lsd.logement.entity.stock.LigneCommande;
import com.lsd.logement.mapper.LigneCommandeMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.LigneCommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/ligne-commande")
@RestController
public class LigneCommandeControllerImpl implements LigneCommandeController {
    private final LigneCommandeService ligneCommandeService;
    private final LigneCommandeMapper ligneCommandeMapper;

    public LigneCommandeControllerImpl(LigneCommandeService ligneCommandeService, LigneCommandeMapper ligneCommandeMapper) {
        this.ligneCommandeService = ligneCommandeService;
        this.ligneCommandeMapper = ligneCommandeMapper;
    }

    public ResponseEntity<ApiResponse<?>> save(@RequestBody LigneCommandeDTO ligneCommandeDTO) {
        try {
            LigneCommande ligneCommande = ligneCommandeMapper.asEntity(ligneCommandeDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(ligneCommandeMapper.asDTO(ligneCommandeService.save(ligneCommande)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            LigneCommande ligneCommande = ligneCommandeService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(ligneCommandeMapper.asDTO(ligneCommande))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            ligneCommandeService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "LigneCommande supprimé avec succes")
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
                    new ApiResponse<>(ligneCommandeMapper.asDTOList(ligneCommandeService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<LigneCommande> ligneCommandePage = ligneCommandeService.findAll(pageable);
            List<LigneCommandeDTO> dtoList = ligneCommandePage
                    .stream()
                    .map(ligneCommandeMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, ligneCommandePage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody LigneCommandeDTO ligneCommandeDTO, @PathVariable("id") Integer id) {
        try {
            LigneCommande ligneCommande = ligneCommandeMapper.asEntity(ligneCommandeDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(ligneCommandeMapper.asDTO(ligneCommandeService.update(ligneCommande, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}