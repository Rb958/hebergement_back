package com.lsd.logement.service;

import com.lsd.logement.entity.personnel.User;
import com.lsd.logement.security.payload.response.JwtResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends GenericService<User, Integer> {
    boolean checkAdmin();
    JwtResponse login(User user);
    User enableUser(Integer id);
    Page<User> findAllUser(Pageable pageable);
}