package com.lsd.logement.service.impl;

import com.lsd.logement.dao.CaisseRepository;
import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.entity.finance.StatutCaisse;
import com.lsd.logement.service.CaisseService;
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
public class CaisseServiceImpl implements CaisseService {
    private final CaisseRepository repository;

    public CaisseServiceImpl(CaisseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Caisse save(Caisse entity) {
        ZonedDateTime currenDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currenDateTime);
        entity.setLastUpdatedAt(currenDateTime);
        entity.setStatus(StatutCaisse.OUVERT);
        entity.setSolde(0);
        return repository.save(entity);
    }

    @Override
    public List<Caisse> save(List<Caisse> entities) {
        return (List<Caisse>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Caisse> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Caisse> findAll() {
        return (List<Caisse>) repository.findAll();
    }

    @Override
    public Page<Caisse> findAll(Pageable pageable) {
        Page<Caisse> entityPage = repository.findAll(pageable);
        List<Caisse> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Caisse update(Caisse entity, Integer id) {
        Optional<Caisse> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            entity.setStatus(optional.get().getStatus());
            return save(entity);
        }
        return null;
    }
}