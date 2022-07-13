package com.lsd.logement.controller;

import com.lsd.logement.dto.FournisseurDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Fournisseur API")
public interface FournisseurController {
    @ApiOperation("Add new data")
    public FournisseurDTO save(@RequestBody FournisseurDTO fournisseur);

    @ApiOperation("Find by Id")
    public FournisseurDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<FournisseurDTO> list();

    @ApiOperation("Pagination request")
    public Page<FournisseurDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public FournisseurDTO update(@RequestBody FournisseurDTO dto, @PathVariable("id") Integer id);
}