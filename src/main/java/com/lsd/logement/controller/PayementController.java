package com.lsd.logement.controller;

import com.lsd.logement.dto.PayementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Api(tags = "Payement API")
public interface PayementController {
//
    public PayementDTO save(@RequestBody PayementDTO payement);

//
    public PayementDTO findById(@PathVariable("id") Integer id);

//
    public void delete(@PathVariable("id") Integer id);

//    
    public List<PayementDTO> list();

//    
    public Page<PayementDTO> pageQuery(Pageable pageable);

//    
    public PayementDTO update(@RequestBody PayementDTO dto, @PathVariable("id") Integer id);
}