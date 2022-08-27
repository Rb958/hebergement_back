package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.StatsController;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/stats")
@RestController
public class StatsControllerImpl implements StatsController {

    private final StatService statService;

    public StatsControllerImpl(StatService statService) {
        this.statService = statService;
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getStats() {
        try{
            return ResponseEntity.ok(
                    new ApiResponse<>(statService.getDashboardStat())
            );
        }catch(Exception e){
            return ResponseEntity.ok(
                    ApiResponse.from(e)
            );
        }
    }
}
