package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.LocalController;
import com.lsd.logement.dto.LocalDTO;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.mapper.LocalMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.LocalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/local")
@RestController
public class LocalControllerImpl implements LocalController {
    private final LocalService localService;
    private final LocalMapper localMapper;

    public LocalControllerImpl(LocalService localService, LocalMapper localMapper) {
        this.localService = localService;
        this.localMapper = localMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody LocalDTO localDTO) {
        try {
            Local local = localMapper.asEntity(localDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(localMapper.asDTO(localService.save(local)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {

        try {
            Local local = localService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(localMapper.asDTO(local))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            localService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>("Successfully deleted")
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
                    new ApiResponse<>(localMapper.asDTOList(localService.findAll()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Local> localPage = localService.findAll(pageable);
            List<LocalDTO> dtoList = localPage
                    .stream()
                    .map(localMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, localPage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody LocalDTO localDTO, @PathVariable("id") Integer id) {
        try {
            Local local = localMapper.asEntity(localDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(localMapper.asDTO(localService.update(local, id)))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/available")
    public ResponseEntity<ApiResponse<?>> findAllAvailable(Pageable pageable) {
        try {
            Page<Local> localPage = localService.findAllAvailable(pageable);
            List<LocalDTO> dtoList = localPage
                    .stream()
                    .map(localMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, localPage.getTotalElements()))
            );
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}