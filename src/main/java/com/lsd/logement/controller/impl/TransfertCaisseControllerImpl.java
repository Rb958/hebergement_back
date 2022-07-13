package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.TransfertCaisseController;
import com.lsd.logement.dto.TransfertCaisseDTO;
import com.lsd.logement.entity.finance.TransfertCaisse;
import com.lsd.logement.mapper.TransfertCaisseMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.TransfertCaisseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/transfert-caisse")
@RestController
public class TransfertCaisseControllerImpl implements TransfertCaisseController {
    private final TransfertCaisseService transfertCaisseService;
    private final TransfertCaisseMapper transfertCaisseMapper;

    public TransfertCaisseControllerImpl(TransfertCaisseService transfertCaisseService, TransfertCaisseMapper transfertCaisseMapper) {
        this.transfertCaisseService = transfertCaisseService;
        this.transfertCaisseMapper = transfertCaisseMapper;
    }

    public ResponseEntity<ApiResponse<?>> save(@RequestBody TransfertCaisseDTO transfertCaisseDTO) {
        try {
            TransfertCaisse transfertCaisse = transfertCaisseMapper.asEntity(transfertCaisseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(transfertCaisseMapper.asDTO(transfertCaisseService.save(transfertCaisse)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            TransfertCaisse transfertCaisse = transfertCaisseService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(transfertCaisseMapper.asDTO(transfertCaisse))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            transfertCaisseService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "TransfertCaisse supprimé avec succes")
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
                    new ApiResponse<>(transfertCaisseMapper.asDTOList(transfertCaisseService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<TransfertCaisse> transfertCaissePage = transfertCaisseService.findAll(pageable);
            List<TransfertCaisseDTO> dtoList = transfertCaissePage
                    .stream()
                    .map(transfertCaisseMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, transfertCaissePage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody TransfertCaisseDTO transfertCaisseDTO, @PathVariable("id") Integer id) {
        try {
            TransfertCaisse transfertCaisse = transfertCaisseMapper.asEntity(transfertCaisseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(transfertCaisseMapper.asDTO(transfertCaisseService.update(transfertCaisse, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}