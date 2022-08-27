package com.lsd.logement.service.impl;

import com.lsd.logement.dao.UserRepository;
import com.lsd.logement.entity.personnel.User;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.UserExceptionMessage;
import com.lsd.logement.security.jwt.JwtUtils;
import com.lsd.logement.security.payload.response.JwtResponse;
import com.lsd.logement.security.services.UserDetailsImpl;
import com.lsd.logement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository repository, JwtUtils jwtUtils, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User entity) {
        entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
        if (entity.getRoles() == null || entity.getRoles().isEmpty()){
            entity.setRoles("ROLE_USER");
        }
        return repository.save(entity);
    }

    @Override
    public List<User> save(List<User> entities) {
        return (List<User>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> entityPage = repository.findAll(pageable);
        List<User> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public User update(User entity, Integer id) {
        Optional<User> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }

    @Override
    public JwtResponse login(User user) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (!userDetails.getEnable()){
            throw new GeneralBaseException(UserExceptionMessage.USER_IS_DISABLED);
        }

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        User tmpUser = repository.findByUsername(userDetails.getUsername());
        tmpUser.setConnectedAt(ZonedDateTime.now());
        tmpUser = repository.save(tmpUser);
        return new JwtResponse(jwtToken,tmpUser,roles);
    }

    @Override
    public boolean checkAdmin() {
        return repository.existsByRoles("ROLE_ADMIN");
    }
}