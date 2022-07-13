package com.lsd.logement.controller;

import com.lsd.logement.dto.LigneCommandeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "LigneCommande API")
public interface LigneCommandeController {
    @ApiOperation("Add new data")
    public LigneCommandeDTO save(@RequestBody LigneCommandeDTO ligneCommande);

    @ApiOperation("Find by Id")
    public LigneCommandeDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<LigneCommandeDTO> list();

    @ApiOperation("Pagination request")
    public Page<LigneCommandeDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public LigneCommandeDTO update(@RequestBody LigneCommandeDTO dto, @PathVariable("id") Integer id);
}