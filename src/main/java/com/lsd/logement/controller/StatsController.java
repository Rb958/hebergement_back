package com.lsd.logement.controller;

import com.lsd.logement.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface StatsController {

    ResponseEntity<ApiResponse<?>> getStats();
}
