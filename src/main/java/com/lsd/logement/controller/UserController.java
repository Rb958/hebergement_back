package com.lsd.logement.controller;

import com.lsd.logement.dto.UserDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserController {
    public ResponseEntity<ApiResponse<?>> save(@RequestBody UserDTO user);

    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);

    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

    public ResponseEntity<ApiResponse<?>> list();

    public ResponseEntity<ApiResponse<?>> enable(@PathVariable("id") Integer id);

    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

    public ResponseEntity<ApiResponse<?>> update(@RequestBody UserDTO dto, @PathVariable("id") Integer id);
}