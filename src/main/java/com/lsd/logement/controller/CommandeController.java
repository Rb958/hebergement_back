package com.lsd.logement.controller;

import com.lsd.logement.dto.CommandeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Commande API")
public interface CommandeController {
    @ApiOperation("Add new data")
    public CommandeDTO save(@RequestBody CommandeDTO commande);

    @ApiOperation("Find by Id")
    public CommandeDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<CommandeDTO> list();

    @ApiOperation("Pagination request")
    public Page<CommandeDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public CommandeDTO update(@RequestBody CommandeDTO dto, @PathVariable("id") Integer id);
}