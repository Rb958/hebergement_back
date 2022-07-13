package com.lsd.logement.controller;

import com.lsd.logement.dto.LocataireSocieteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Api(tags = "LocataireSociete API")
public interface LocataireSocieteController {
//
    public LocataireSocieteDTO save(@RequestBody LocataireSocieteDTO locataireSociete);

//
    public LocataireSocieteDTO findById(@PathVariable("id") Integer id);

//
    public void delete(@PathVariable("id") Integer id);

//    
    public List<LocataireSocieteDTO> list();

//    
    public Page<LocataireSocieteDTO> pageQuery(Pageable pageable);

//    
    public LocataireSocieteDTO update(@RequestBody LocataireSocieteDTO dto, @PathVariable("id") Integer id);
}