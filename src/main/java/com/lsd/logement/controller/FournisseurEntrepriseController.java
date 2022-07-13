package com.lsd.logement.controller;

import com.lsd.logement.dto.FournisseurEntrepriseDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface FournisseurEntrepriseController {

    public ResponseEntity<ApiResponse<?>> save(@RequestBody FournisseurEntrepriseDTO fournisseurEntreprise);


    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);


    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

    
    public ResponseEntity<ApiResponse<?>> list();

    
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

    
    public ResponseEntity<ApiResponse<?>> update(@RequestBody FournisseurEntrepriseDTO dto, @PathVariable("id") Integer id);
}