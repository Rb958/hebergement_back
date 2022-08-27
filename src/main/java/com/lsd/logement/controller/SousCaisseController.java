package com.lsd.logement.controller;

import com.lsd.logement.dto.SousCaisseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SousCaisseController {
    public SousCaisseDTO save(@RequestBody SousCaisseDTO sousCaisse);

    public SousCaisseDTO findById(@PathVariable("id") Integer id);

    public void delete(@PathVariable("id") Integer id);

    public List<SousCaisseDTO> list();

    public Page<SousCaisseDTO> pageQuery(Pageable pageable);

    public SousCaisseDTO update(@RequestBody SousCaisseDTO dto, @PathVariable("id") Integer id);
}