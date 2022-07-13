package com.lsd.logement.controller;

import com.lsd.logement.dto.LocataireSocieteDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//@Api(tags = "Locataires API")
public interface LocatairesSocieteController {
//    @ApiOperation("Add new data")
    public ResponseEntity<ApiResponse<?>> save(@RequestBody LocataireSocieteDTO locataires);

//    @ApiOperation("Find by Id")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);

//    @ApiOperation("Delete based on primary key")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

//    @ApiOperation("Find all data")
    public ResponseEntity<ApiResponse<?>> list();

//    @ApiOperation("Pagination request")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

//    @ApiOperation("Update one data")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody LocataireSocieteDTO dto, @PathVariable("id") Integer id);
}