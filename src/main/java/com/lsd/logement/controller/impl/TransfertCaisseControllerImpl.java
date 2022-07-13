package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.TransfertCaisseController;
import com.lsd.logement.dto.TransfertCaisseDTO;
import com.lsd.logement.entity.finance.TransfertCaisse;
import com.lsd.logement.mapper.TransfertCaisseMapper;
import com.lsd.logement.service.TransfertCaisseService;
import okhttp3.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/transfert-caisse")
@RestController
public class TransfertCaisseControllerImpl implements TransfertCaisseController {
    private final TransfertCaisseService transfertCaisseService;
    private final TransfertCaisseMapper transfertCaisseMapper;

    public TransfertCaisseControllerImpl(TransfertCaisseService transfertCaisseService, TransfertCaisseMapper transfertCaisseMapper) {
        this.transfertCaisseService = transfertCaisseService;
        this.transfertCaisseMapper = transfertCaisseMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransfertCaisseDTO save(@RequestBody TransfertCaisseDTO transfertCaisseDTO) {
        TransfertCaisse transfertCaisse = transfertCaisseMapper.asEntity(transfertCaisseDTO);
        return transfertCaisseMapper.asDTO(transfertCaisseService.save(transfertCaisse));
    }

    @Override
    @GetMapping("/{id}")
    public TransfertCaisseDTO findById(@PathVariable("id") Integer id) {
        TransfertCaisse transfertCaisse = transfertCaisseService.findById(id).orElse(null);
        return transfertCaisseMapper.asDTO(transfertCaisse);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        transfertCaisseService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<TransfertCaisseDTO> list() {
        return transfertCaisseMapper.asDTOList(transfertCaisseService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<TransfertCaisseDTO> pageQuery(Pageable pageable) {
        Page<TransfertCaisse> transfertCaissePage = transfertCaisseService.findAll(pageable);
        List<TransfertCaisseDTO> dtoList = transfertCaissePage
                .stream()
                .map(transfertCaisseMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, transfertCaissePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public TransfertCaisseDTO update(@RequestBody TransfertCaisseDTO transfertCaisseDTO, @PathVariable("id") Integer id) {
        TransfertCaisse transfertCaisse = transfertCaisseMapper.asEntity(transfertCaisseDTO);
        return transfertCaisseMapper.asDTO(transfertCaisseService.update(transfertCaisse, id));
    }
}