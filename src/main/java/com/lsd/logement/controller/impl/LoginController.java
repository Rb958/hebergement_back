package com.lsd.logement.controller.impl;

import com.lsd.logement.dto.UserDTO;
import com.lsd.logement.mapper.UserMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final UserService userService;
    private final UserMapper userMapper;

    Logger logger = LogManager.getLogger(LoginController.class);

    public LoginController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> authenticateUser(@Valid @RequestBody UserDTO utilisateur){
        try {
            return ResponseEntity.ok(new ApiResponse<>(this.userService.login(userMapper.asEntity(utilisateur))));
        }catch(Exception e){
            return ResponseEntity.ok().body(
                    ApiResponse.from(e)
            );
        }
    }
}
