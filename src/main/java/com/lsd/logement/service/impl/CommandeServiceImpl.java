package com.lsd.logement.service.impl;

import com.lsd.logement.dao.CommandeRepository;
import com.lsd.logement.entity.stock.Commande;
import com.lsd.logement.service.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository repository;

    public CommandeServiceImpl(CommandeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Commande save(Commande entity) {
        return repository.save(entity);
    }

    @Override
    public List<Commande> save(List<Commande> entities) {
        return (List<Commande>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Commande> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Commande> findAll() {
        return (List<Commande>) repository.findAll();
    }

    @Override
    public Page<Commande> findAll(Pageable pageable) {
        Page<Commande> entityPage = repository.findAll(pageable);
        List<Commande> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Commande update(Commande entity, Integer id) {
        Optional<Commande> optional = findById(id) );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}