package com.lsd.logement.dao;

import com.lsd.logement.entity.personnel.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByRoles(String role_admin);

    Page<User> findAllByRolesIsNot(String role, Pageable pageable);
}