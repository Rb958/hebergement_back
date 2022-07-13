package com.lsd.logement.controller;

import com.lsd.logement.dto.CaisseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Caisse API")
public interface CaisseController {
    @ApiOperation("Add new data")
    public CaisseDTO save(@RequestBody CaisseDTO caisse);

    @ApiOperation("Find by Id")
    public CaisseDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<CaisseDTO> list();

    @ApiOperation("Pagination request")
    public Page<CaisseDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public CaisseDTO update(@RequestBody CaisseDTO dto, @PathVariable("id") Integer id);
}