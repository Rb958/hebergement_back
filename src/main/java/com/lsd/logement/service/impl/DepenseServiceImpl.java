package com.lsd.logement.service.impl;

import com.lsd.logement.dao.DepenseRepository;
import com.lsd.logement.entity.finance.Depense;
import com.lsd.logement.entity.finance.StatutDepense;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.NotFoundMessage;
import com.lsd.logement.service.CaisseService;
import com.lsd.logement.service.DepenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepenseServiceImpl implements DepenseService {
    private final DepenseRepository repository;

    private final CaisseService caisseService;

    public DepenseServiceImpl(DepenseRepository repository, CaisseService caisseService) {
        this.repository = repository;
        this.caisseService = caisseService;
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

    @Override
    public long countDepenseMois() {
        ZonedDateTime startMonth = ZonedDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        ZonedDateTime endMonth = ZonedDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        Optional<Long> depense = repository.getDepenseOfMonth(startMonth, endMonth);
        return depense.orElse(0L);
    }

    @Override
    public long countDepenseAnnee() {
        ZonedDateTime startYear = ZonedDateTime.now().with(TemporalAdjusters.firstDayOfYear());
        ZonedDateTime endYear = ZonedDateTime.now().with(TemporalAdjusters.lastDayOfYear());
        return repository.getDepenseOfMonth(startYear, endYear).orElse(0L);
    }

    @Override
    @Transactional
    public void validate(Depense depense) {
        Optional<Depense> optional = repository.findById(depense.getId());
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.DEPENSE_NOT_FOUND);
        }
        Depense tmp = optional.get();
        tmp.setStatus(StatutDepense.VALIDE);
        tmp.setLastUpdatedAt(ZonedDateTime.now());
        this.caisseService.debitPrincipal(depense.getMontant());
        repository.save(depense);
    }
}