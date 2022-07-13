package com.lsd.logement.service;

import com.lsd.logement.entity.personnel.User;
import com.lsd.logement.security.payload.response.JwtResponse;

public interface UserService extends GenericService<User, Integer> {
    boolean checkAdmin();
    JwtResponse login(User user);
}