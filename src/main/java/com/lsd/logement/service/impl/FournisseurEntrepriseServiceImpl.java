package com.lsd.logement.service.impl;

import com.lsd.logement.dao.FournisseurEntrepriseRepository;
import com.lsd.logement.entity.stock.FournisseurEntreprise;
import com.lsd.logement.service.FournisseurEntrepriseService;
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
public class FournisseurEntrepriseServiceImpl implements FournisseurEntrepriseService {
    private final FournisseurEntrepriseRepository repository;

    public FournisseurEntrepriseServiceImpl(FournisseurEntrepriseRepository repository) {
        this.repository = repository;
    }

    @Override
    public FournisseurEntreprise save(FournisseurEntreprise entity) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currentDateTime);
        entity.setLastUpdatedAt(currentDateTime);
        return repository.save(entity);
    }

    @Override
    public List<FournisseurEntreprise> save(List<FournisseurEntreprise> entities) {
        return (List<FournisseurEntreprise>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<FournisseurEntreprise> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<FournisseurEntreprise> findAll() {
        return (List<FournisseurEntreprise>) repository.findAll();
    }

    @Override
    public Page<FournisseurEntreprise> findAll(Pageable pageable) {
        Page<FournisseurEntreprise> entityPage = repository.findAll(pageable);
        List<FournisseurEntreprise> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public FournisseurEntreprise update(FournisseurEntreprise entity, Integer id) {
        Optional<FournisseurEntreprise> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }
}