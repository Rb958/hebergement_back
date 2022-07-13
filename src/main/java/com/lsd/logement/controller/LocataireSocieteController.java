package com.lsd.logement.controller;

import com.lsd.logement.dto.LocataireSocieteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Api(tags = "LocataireSociete API")
public interface LocataireSocieteController {
//    @ApiOperation("Add new data")
    public LocataireSocieteDTO save(@RequestBody LocataireSocieteDTO locataireSociete);

//    @ApiOperation("Find by Id")
    public LocataireSocieteDTO findById(@PathVariable("id") Integer id);

//    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

//    @ApiOperation("Find all data")
    public List<LocataireSocieteDTO> list();

//    @ApiOperation("Pagination request")
    public Page<LocataireSocieteDTO> pageQuery(Pageable pageable);

//    @ApiOperation("Update one data")
    public LocataireSocieteDTO update(@RequestBody LocataireSocieteDTO dto, @PathVariable("id") Integer id);
}