package com.lsd.logement.service.impl;

import com.lsd.logement.dao.FournisseurRepository;
import com.lsd.logement.entity.stock.Fournisseur;
import com.lsd.logement.service.FournisseurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository repository;

    public FournisseurServiceImpl(FournisseurRepository repository) {
        this.repository = repository;
    }

    @Override
    public Fournisseur save(Fournisseur entity) {
        return repository.save(entity);
    }

    @Override
    public List<Fournisseur> save(List<Fournisseur> entities) {
        return (List<Fournisseur>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Fournisseur> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Fournisseur> findAll() {
        return (List<Fournisseur>) repository.findAll();
    }

    @Override
    public Page<Fournisseur> findAll(Pageable pageable) {
        Page<Fournisseur> entityPage = repository.findAll(pageable);
        List<Fournisseur> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Fournisseur update(Fournisseur entity, Integer id) {
        Optional<Fournisseur> optional = findById(id) );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}