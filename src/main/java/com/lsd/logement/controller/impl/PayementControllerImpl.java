package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.PayementController;
import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.mapper.PayementMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.PayementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/payement")
@RestController
public class PayementControllerImpl implements PayementController {
    private final PayementService payementService;
    private final PayementMapper payementMapper;

    public PayementControllerImpl(PayementService payementService, PayementMapper payementMapper) {
        this.payementService = payementService;
        this.payementMapper = payementMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody PayementDTO payementDTO) {
        try {
            Payement payement = payementMapper.asEntity(payementDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(payementMapper.asDTO(payementService.save(payement)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            Payement payement = payementService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(payementMapper.asDTO(payement))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            payementService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "Payement supprimé avec succes")
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
                    new ApiResponse<>(payementMapper.asDTOList(payementService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Payement> payementPage = payementService.findAll(pageable);
            List<PayementDTO> dtoList = payementPage
                    .stream()
                    .map(payementMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, payementPage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody PayementDTO payementDTO, @PathVariable("id") Integer id) {
        try {
            Payement payement = payementMapper.asEntity(payementDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(payementMapper.asDTO(payementService.update(payement, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}