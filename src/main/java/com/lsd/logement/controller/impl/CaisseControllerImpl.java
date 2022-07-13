package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.CaisseController;
import com.lsd.logement.dto.CaisseDTO;
import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.mapper.CaisseMapper;
import com.lsd.logement.service.CaisseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/caisse")
@RestController
public class CaisseControllerImpl implements CaisseController {
    private final CaisseService caisseService;
    private final CaisseMapper caisseMapper;

    public CaisseControllerImpl(CaisseService caisseService, CaisseMapper caisseMapper) {
        this.caisseService = caisseService;
        this.caisseMapper = caisseMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CaisseDTO save(@RequestBody CaisseDTO caisseDTO) {
        Caisse caisse = caisseMapper.asEntity(caisseDTO);
        return caisseMapper.asDTO(caisseService.save(caisse));
    }

    @Override
    @GetMapping("/{id}")
    public CaisseDTO findById(@PathVariable("id") Integer id) {
        Caisse caisse = caisseService.findById(id).orElse(null);
        return caisseMapper.asDTO(caisse);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        caisseService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<CaisseDTO> list() {
        return caisseMapper.asDTOList(caisseService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<CaisseDTO> pageQuery(Pageable pageable) {
        Page<Caisse> caissePage = caisseService.findAll(pageable);
        List<CaisseDTO> dtoList = caissePage
                .stream()
                .map(caisseMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, caissePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public CaisseDTO update(@RequestBody CaisseDTO caisseDTO, @PathVariable("id") Integer id) {
        Caisse caisse = caisseMapper.asEntity(caisseDTO);
        return caisseMapper.asDTO(caisseService.update(caisse, id));
    }
}