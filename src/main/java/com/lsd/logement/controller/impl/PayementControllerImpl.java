package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.PayementController;
import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.mapper.PayementMapper;
import com.lsd.logement.service.PayementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public PayementDTO save(@RequestBody PayementDTO payementDTO) {
        Payement payement = payementMapper.asEntity(payementDTO);
        return payementMapper.asDTO(payementService.save(payement));
    }

    @Override
    @GetMapping("/{id}")
    public PayementDTO findById(@PathVariable("id") Integer id) {
        Payement payement = payementService.findById(id).orElse(null);
        return payementMapper.asDTO(payement);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        payementService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<PayementDTO> list() {
        return payementMapper.asDTOList(payementService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<PayementDTO> pageQuery(Pageable pageable) {
        Page<Payement> payementPage = payementService.findAll(pageable);
        List<PayementDTO> dtoList = payementPage
                .stream()
                .map(payementMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, payementPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public PayementDTO update(@RequestBody PayementDTO payementDTO, @PathVariable("id") Integer id) {
        Payement payement = payementMapper.asEntity(payementDTO);
        return payementMapper.asDTO(payementService.update(payement, id));
    }
}