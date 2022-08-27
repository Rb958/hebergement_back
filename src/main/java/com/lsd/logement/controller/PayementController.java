package com.lsd.logement.controller;

import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PayementController {
     ResponseEntity<ApiResponse<?>> save(@RequestBody PayementDTO payement);

     ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);

     ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

     ResponseEntity<ApiResponse<?>> list();

     ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

     ResponseEntity<ApiResponse<?>> update(@RequestBody PayementDTO dto, @PathVariable("id") Integer id);
}