package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.DepenseController;
import com.lsd.logement.dto.DepenseDTO;
import com.lsd.logement.entity.finance.Depense;
import com.lsd.logement.mapper.DepenseMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.DepenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/depense")
@RestController
public class DepenseControllerImpl implements DepenseController {
    private final DepenseService depenseService;
    private final DepenseMapper depenseMapper;

    public DepenseControllerImpl(DepenseService depenseService, DepenseMapper depenseMapper) {
        this.depenseService = depenseService;
        this.depenseMapper = depenseMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody DepenseDTO depenseDTO) {
        try {
            Depense depense = depenseMapper.asEntity(depenseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(depenseMapper.asDTO(depenseService.save(depense)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            Depense depense = depenseService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(depenseMapper.asDTO(depense))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            depenseService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "Depense supprimé avec succes")
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
                    new ApiResponse<>(depenseMapper.asDTOList(depenseService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Depense> depensePage = depenseService.findAll(pageable);
            List<DepenseDTO> dtoList = depensePage
                    .stream()
                    .map(depenseMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, depensePage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody DepenseDTO depenseDTO, @PathVariable("id") Integer id) {
        try {
            Depense depense = depenseMapper.asEntity(depenseDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(depenseMapper.asDTO(depenseService.update(depense, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}