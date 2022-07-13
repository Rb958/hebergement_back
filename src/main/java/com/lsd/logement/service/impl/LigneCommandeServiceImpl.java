package com.lsd.logement.service.impl;

import com.lsd.logement.dao.LigneCommandeRepository;
import com.lsd.logement.entity.stock.LigneCommande;
import com.lsd.logement.service.LigneCommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService {
    private final LigneCommandeRepository repository;

    public LigneCommandeServiceImpl(LigneCommandeRepository repository) {
        this.repository = repository;
    }

    @Override
    public LigneCommande save(LigneCommande entity) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currentDateTime);
        entity.setLastUpdatedAt(currentDateTime);
        return repository.save(entity);
    }

    @Override
    public List<LigneCommande> save(List<LigneCommande> entities) {
        return (List<LigneCommande>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<LigneCommande> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<LigneCommande> findAll() {
        return (List<LigneCommande>) repository.findAll();
    }

    @Override
    public Page<LigneCommande> findAll(Pageable pageable) {
        Page<LigneCommande> entityPage = repository.findAll(pageable);
        List<LigneCommande> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public LigneCommande update(LigneCommande entity, Integer id) {
        Optional<LigneCommande> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }
}