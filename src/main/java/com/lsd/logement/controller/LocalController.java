package com.lsd.logement.controller;

import com.lsd.logement.dto.LocalDTO;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.model.LocalAvailableSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface LocalController {
    ResponseEntity<ApiResponse<?>> save(@RequestBody LocalDTO local);

    ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> list();

    ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

    ResponseEntity<ApiResponse<?>> findAllAvailable(Pageable pageable);

    ResponseEntity<ApiResponse<?>> update(@RequestBody LocalDTO dto, @PathVariable("id") Integer id);

    ResponseEntity<ApiResponse<?>> findAvailableLocal(@RequestBody LocalAvailableSearch search);

    ResponseEntity<ApiResponse<?>> findAvailableLocalNonMeuble(@RequestBody LocalAvailableSearch search);
}