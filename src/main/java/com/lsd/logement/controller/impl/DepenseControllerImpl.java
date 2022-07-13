package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.DepenseController;
import com.lsd.logement.dto.DepenseDTO;
import com.lsd.logement.entity.finance.Depense;
import com.lsd.logement.mapper.DepenseMapper;
import com.lsd.logement.service.DepenseService;
import okhttp3.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/depense")
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
    @ResponseStatus(HttpStatus.CREATED)
    public DepenseDTO save(@RequestBody DepenseDTO depenseDTO) {
        Depense depense = depenseMapper.asEntity(depenseDTO);
        return depenseMapper.asDTO(depenseService.save(depense));
    }

    @Override
    @GetMapping("/{id}")
    public DepenseDTO findById(@PathVariable("id") Integer id) {
        Depense depense = depenseService.findById(id).orElse(null);
        return depenseMapper.asDTO(depense);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        depenseService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<DepenseDTO> list() {
        return depenseMapper.asDTOList(depenseService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<DepenseDTO> pageQuery(Pageable pageable) {
        Page<Depense> depensePage = depenseService.findAll(pageable);
        List<DepenseDTO> dtoList = depensePage
                .stream()
                .map(depenseMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, depensePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public DepenseDTO update(@RequestBody DepenseDTO depenseDTO, @PathVariable("id") Integer id) {
        Depense depense = depenseMapper.asEntity(depenseDTO);
        return depenseMapper.asDTO(depenseService.update(depense, id));
    }
}