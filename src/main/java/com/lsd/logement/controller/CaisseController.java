package com.lsd.logement.controller;

import com.lsd.logement.dto.CaisseDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CaisseController {

    ResponseEntity<ApiResponse<?>> save(@RequestBody CaisseDTO caisse);

    ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> list();

    ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

    ResponseEntity<ApiResponse<?>> update(@RequestBody CaisseDTO dto, @PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> findUserOpenedCaisse(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> findUserCaisse(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> openCaisse(@RequestBody CaisseDTO caisseDTO);

    ResponseEntity<ApiResponse<?>> closeCaisseRequest(@RequestBody CaisseDTO caisseDTO);

    ResponseEntity<ApiResponse<?>> closeCaisse(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> openCaisse(@PathVariable Integer id);

    ResponseEntity<?> downloadCaisseReport(@PathVariable Integer id);
}