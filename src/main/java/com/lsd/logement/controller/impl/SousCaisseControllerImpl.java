package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.SousCaisseController;
import com.lsd.logement.dto.SousCaisseDTO;
import com.lsd.logement.entity.SousCaisse;
import com.lsd.logement.mapper.SousCaisseMapper;
import com.lsd.logement.service.SousCaisseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/sous-caisse")
@RestController
public class SousCaisseControllerImpl implements SousCaisseController {
    private final SousCaisseService sousCaisseService;
    private final SousCaisseMapper sousCaisseMapper;

    public SousCaisseControllerImpl(SousCaisseService sousCaisseService, SousCaisseMapper sousCaisseMapper) {
        this.sousCaisseService = sousCaisseService;
        this.sousCaisseMapper = sousCaisseMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SousCaisseDTO save(@RequestBody SousCaisseDTO sousCaisseDTO) {
        SousCaisse sousCaisse = sousCaisseMapper.asEntity(sousCaisseDTO);
        return sousCaisseMapper.asDTO(sousCaisseService.save(sousCaisse));
    }

    @Override
    @GetMapping("/{id}")
    public SousCaisseDTO findById(@PathVariable("id") Integer id) {
        SousCaisse sousCaisse = sousCaisseService.findById(id).orElse(null);
        return sousCaisseMapper.asDTO(sousCaisse);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        sousCaisseService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<SousCaisseDTO> list() {
        return sousCaisseMapper.asDTOList(sousCaisseService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<SousCaisseDTO> pageQuery(Pageable pageable) {
        Page<SousCaisse> sousCaissePage = sousCaisseService.findAll(pageable);
        List<SousCaisseDTO> dtoList = sousCaissePage
                .stream()
                .map(sousCaisseMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, sousCaissePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public SousCaisseDTO update(@RequestBody SousCaisseDTO sousCaisseDTO, @PathVariable("id") Integer id) {
        SousCaisse sousCaisse = sousCaisseMapper.asEntity(sousCaisseDTO);
        return sousCaisseMapper.asDTO(sousCaisseService.update(sousCaisse, id));
    }
}