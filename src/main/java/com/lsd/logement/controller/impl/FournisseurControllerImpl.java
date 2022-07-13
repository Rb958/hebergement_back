package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.FournisseurController;
import com.lsd.logement.dto.FournisseurDTO;
import com.lsd.logement.entity.stock.Fournisseur;
import com.lsd.logement.mapper.FournisseurMapper;
import com.lsd.logement.service.FournisseurService;
import okhttp3.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/fournisseur")
@RestController
public class FournisseurControllerImpl implements FournisseurController {
    private final FournisseurService fournisseurService;
    private final FournisseurMapper fournisseurMapper;

    public FournisseurControllerImpl(FournisseurService fournisseurService, FournisseurMapper fournisseurMapper) {
        this.fournisseurService = fournisseurService;
        this.fournisseurMapper = fournisseurMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FournisseurDTO save(@RequestBody FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur = fournisseurMapper.asEntity(fournisseurDTO);
        return fournisseurMapper.asDTO(fournisseurService.save(fournisseur));
    }

    @Override
    @GetMapping("/{id}")
    public FournisseurDTO findById(@PathVariable("id") Integer id) {
        Fournisseur fournisseur = fournisseurService.findById(id).orElse(null);
        return fournisseurMapper.asDTO(fournisseur);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        fournisseurService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<FournisseurDTO> list() {
        return fournisseurMapper.asDTOList(fournisseurService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<FournisseurDTO> pageQuery(Pageable pageable) {
        Page<Fournisseur> fournisseurPage = fournisseurService.findAll(pageable);
        List<FournisseurDTO> dtoList = fournisseurPage
                .stream()
                .map(fournisseurMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, fournisseurPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public FournisseurDTO update(@RequestBody FournisseurDTO fournisseurDTO, @PathVariable("id") Integer id) {
        Fournisseur fournisseur = fournisseurMapper.asEntity(fournisseurDTO);
        return fournisseurMapper.asDTO(fournisseurService.update(fournisseur, id));
    }
}