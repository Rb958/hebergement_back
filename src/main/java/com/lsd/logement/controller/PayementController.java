package com.lsd.logement.controller;

import com.lsd.logement.dto.PayementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Api(tags = "Payement API")
public interface PayementController {
//    @ApiOperation("Add new data")
    public PayementDTO save(@RequestBody PayementDTO payement);

//    @ApiOperation("Find by Id")
    public PayementDTO findById(@PathVariable("id") Integer id);

//    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

//    @ApiOperation("Find all data")
    public List<PayementDTO> list();

//    @ApiOperation("Pagination request")
    public Page<PayementDTO> pageQuery(Pageable pageable);

//    @ApiOperation("Update one data")
    public PayementDTO update(@RequestBody PayementDTO dto, @PathVariable("id") Integer id);
}