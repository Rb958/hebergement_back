package com.lsd.logement.controller;

import com.lsd.logement.dto.TransfertCaisseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "TransfertCaisse API")
public interface TransfertCaisseController {
    @ApiOperation("Add new data")
    public TransfertCaisseDTO save(@RequestBody TransfertCaisseDTO transfertCaisse);

    @ApiOperation("Find by Id")
    public TransfertCaisseDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<TransfertCaisseDTO> list();

    @ApiOperation("Pagination request")
    public Page<TransfertCaisseDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public TransfertCaisseDTO update(@RequestBody TransfertCaisseDTO dto, @PathVariable("id") Integer id);
}