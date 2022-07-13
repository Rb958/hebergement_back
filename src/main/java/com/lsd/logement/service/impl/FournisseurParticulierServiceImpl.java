package com.lsd.logement.service.impl;

import com.lsd.logement.dao.FournisseurParticulierRepository;
import com.lsd.logement.entity.stock.FournisseurParticulier;
import com.lsd.logement.service.FournisseurParticulierService;
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
public class FournisseurParticulierServiceImpl implements FournisseurParticulierService {
    private final FournisseurParticulierRepository repository;

    public FournisseurParticulierServiceImpl(FournisseurParticulierRepository repository) {
        this.repository = repository;
    }

    @Override
    public FournisseurParticulier save(FournisseurParticulier entity) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currentDateTime);
        entity.setLastUpdatedAt(currentDateTime);
        return repository.save(entity);
    }

    @Override
    public List<FournisseurParticulier> save(List<FournisseurParticulier> entities) {
        return (List<FournisseurParticulier>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<FournisseurParticulier> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<FournisseurParticulier> findAll() {
        return (List<FournisseurParticulier>) repository.findAll();
    }

    @Override
    public Page<FournisseurParticulier> findAll(Pageable pageable) {
        Page<FournisseurParticulier> entityPage = repository.findAll(pageable);
        List<FournisseurParticulier> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public FournisseurParticulier update(FournisseurParticulier entity, Integer id) {
        Optional<FournisseurParticulier> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }
}