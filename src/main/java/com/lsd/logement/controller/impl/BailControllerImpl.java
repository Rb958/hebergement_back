package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.BailController;
import com.lsd.logement.dto.BailDTO;
import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.entity.reservation.Bail;
import com.lsd.logement.mapper.BailMapper;
import com.lsd.logement.mapper.PayementMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.BailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/bail")
@RestController
public class BailControllerImpl implements BailController {
    private final BailService bailService;
    private final BailMapper bailMapper;
    private final PayementMapper payementMapper;

    public BailControllerImpl(BailService bailService, BailMapper bailMapper, PayementMapper payementMapper) {
        this.bailService = bailService;
        this.bailMapper = bailMapper;
        this.payementMapper = payementMapper;
    }

    @Override
    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> save(@RequestBody BailDTO bailDTO, @PathVariable("userId") int userId) {
        try {
            Bail bail = bailMapper.asEntity(bailDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(bailMapper.asDTO(bailService.save(bail, userId)))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            Bail bail = bailService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(bailMapper.asDTO(bail))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            bailService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(200, "Supprimer avec succes")
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(bailMapper.asDTOList(bailService.findAll())));
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Bail> bailPage = bailService.findAll(pageable);
            List<BailDTO> dtoList = bailPage
                    .stream()
                    .map(bailMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, bailPage.getTotalElements()))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody BailDTO bailDTO, @PathVariable("id") Integer id) {
        try {
            Bail bail = bailMapper.asEntity(bailDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(bailMapper.asDTO(bailService.update(bail, id)))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @PatchMapping("/user/{userId}/payment/add/{id}")
    @Override
    public ResponseEntity<ApiResponse<?>> addPayment(Integer userId, PayementDTO paymentDto, Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bailMapper.asDTO(bailService.addPayment(userId, id, payementMapper.asEntity(paymentDto))))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PatchMapping("/payment/remove/{id}")
    public ResponseEntity<ApiResponse<?>> removePayment(PayementDTO dto, Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bailMapper.asDTO(bailService.removePayment(id, payementMapper.asEntity(dto))))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PatchMapping("/{bailId}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelBail(Integer bailId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bailMapper.asDTO(bailService.cancelBail(bailId)))
);
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<?>> bailState() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bailService.bailStats())
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}