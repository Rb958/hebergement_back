package com.lsd.logement.controller;

import com.lsd.logement.dto.DepenseDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DepenseController {

    public ResponseEntity<ApiResponse<?>> save(@RequestBody DepenseDTO depense);


    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);


    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

    
    public ResponseEntity<ApiResponse<?>> list();

    
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

    
    public ResponseEntity<ApiResponse<?>> update(@RequestBody DepenseDTO dto, @PathVariable("id") Integer id);
}