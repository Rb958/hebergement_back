package com.lsd.logement.service.impl;

import com.lsd.logement.dao.TransfertCaisseRepository;
import com.lsd.logement.entity.finance.StatutTransfert;
import com.lsd.logement.entity.finance.TransfertCaisse;
import com.lsd.logement.service.TransfertCaisseService;
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
public class TransfertCaisseServiceImpl implements TransfertCaisseService {
    private final TransfertCaisseRepository repository;

    public TransfertCaisseServiceImpl(TransfertCaisseRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransfertCaisse save(TransfertCaisse entity) {
        ZonedDateTime currentDate = ZonedDateTime.now();
        entity.setCreatedAt(currentDate);
        entity.setLastUpdatedAt(currentDate);
        entity.setStatut(StatutTransfert.INITIE);
        return repository.save(entity);
    }

    @Override
    public List<TransfertCaisse> save(List<TransfertCaisse> entities) {
        return (List<TransfertCaisse>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<TransfertCaisse> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<TransfertCaisse> findAll() {
        return (List<TransfertCaisse>) repository.findAll();
    }

    @Override
    public Page<TransfertCaisse> findAll(Pageable pageable) {
        Page<TransfertCaisse> entityPage = repository.findAll(pageable);
        List<TransfertCaisse> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public TransfertCaisse update(TransfertCaisse entity, Integer id) {
        Optional<TransfertCaisse> optional = findById(id);
        if (optional.isPresent()) {
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            entity.setStatut(optional.get().getStatut());
            return save(entity);
        }
        return null;
    }
}