package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.LigneCommandeController;
import com.lsd.logement.dto.LigneCommandeDTO;
import com.lsd.logement.entity.stock.LigneCommande;
import com.lsd.logement.mapper.LigneCommandeMapper;
import com.lsd.logement.service.LigneCommandeService;
import okhttp3.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/ligne-commande")
@RestController
public class LigneCommandeControllerImpl implements LigneCommandeController {
    private final LigneCommandeService ligneCommandeService;
    private final LigneCommandeMapper ligneCommandeMapper;

    public LigneCommandeControllerImpl(LigneCommandeService ligneCommandeService, LigneCommandeMapper ligneCommandeMapper) {
        this.ligneCommandeService = ligneCommandeService;
        this.ligneCommandeMapper = ligneCommandeMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LigneCommandeDTO save(@RequestBody LigneCommandeDTO ligneCommandeDTO) {
        LigneCommande ligneCommande = ligneCommandeMapper.asEntity(ligneCommandeDTO);
        return ligneCommandeMapper.asDTO(ligneCommandeService.save(ligneCommande));
    }

    @Override
    @GetMapping("/{id}")
    public LigneCommandeDTO findById(@PathVariable("id") Integer id) {
        LigneCommande ligneCommande = ligneCommandeService.findById(id).orElse(null);
        return ligneCommandeMapper.asDTO(ligneCommande);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        ligneCommandeService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<LigneCommandeDTO> list() {
        return ligneCommandeMapper.asDTOList(ligneCommandeService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<LigneCommandeDTO> pageQuery(Pageable pageable) {
        Page<LigneCommande> ligneCommandePage = ligneCommandeService.findAll(pageable);
        List<LigneCommandeDTO> dtoList = ligneCommandePage
                .stream()
                .map(ligneCommandeMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, ligneCommandePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public LigneCommandeDTO update(@RequestBody LigneCommandeDTO ligneCommandeDTO, @PathVariable("id") Integer id) {
        LigneCommande ligneCommande = ligneCommandeMapper.asEntity(ligneCommandeDTO);
        return ligneCommandeMapper.asDTO(ligneCommandeService.update(ligneCommande, id));
    }
}