package com.lsd.logement.service.impl;

import com.lsd.logement.dao.DepenseRepository;
import com.lsd.logement.entity.finance.Depense;
import com.lsd.logement.entity.finance.StatutDepense;
import com.lsd.logement.service.DepenseService;
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
public class DepenseServiceImpl implements DepenseService {
    private final DepenseRepository repository;

    public DepenseServiceImpl(DepenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Depense save(Depense entity) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currentDateTime);
        entity.setLastUpdatedAt(currentDateTime);
        entity.setStatus(StatutDepense.EN_COURS);
        return repository.save(entity);
    }

    @Override
    public List<Depense> save(List<Depense> entities) {
        return (List<Depense>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Depense> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Depense> findAll() {
        return (List<Depense>) repository.findAll();
    }

    @Override
    public Page<Depense> findAll(Pageable pageable) {
        Page<Depense> entityPage = repository.findAll(pageable);
        List<Depense> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Depense update(Depense entity, Integer id) {
        Optional<Depense> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setStatus(optional.get().getStatus());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }
}