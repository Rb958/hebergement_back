package com.lsd.logement.controller;

import com.lsd.logement.dto.DepenseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Depense API")
public interface DepenseController {
    @ApiOperation("Add new data")
    public DepenseDTO save(@RequestBody DepenseDTO depense);

    @ApiOperation("Find by Id")
    public DepenseDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<DepenseDTO> list();

    @ApiOperation("Pagination request")
    public Page<DepenseDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public DepenseDTO update(@RequestBody DepenseDTO dto, @PathVariable("id") Integer id);
}