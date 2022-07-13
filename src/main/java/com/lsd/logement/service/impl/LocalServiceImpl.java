package com.lsd.logement.service.impl;

import com.lsd.logement.dao.LocalRepository;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.infra.LocateState;
import com.lsd.logement.service.LocalService;
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
public class LocalServiceImpl implements LocalService {
    private final LocalRepository repository;

    public LocalServiceImpl(LocalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Local save(Local entity) {
        ZonedDateTime currentDate = ZonedDateTime.now();
        entity.setCreatedAt(currentDate);
        entity.setLastUpdatedAt(currentDate);
        entity.setStatus(LocateState.LIBRE);
        return repository.save(entity);
    }

    @Override
    public List<Local> save(List<Local> entities) {
        return (List<Local>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Local> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Local> findAll() {
        return (List<Local>) repository.findAll();
    }

    @Override
    public Page<Local> findAll(Pageable pageable) {
        Page<Local> entityPage = repository.findAll(pageable);
        List<Local> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Local update(Local entity, Integer id) {
        Optional<Local> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }

    @Override
    public Page<Local> findAllAvailable(Pageable pageable) {
        Page<Local> entityPage = repository.findAllAvailable(pageable);
        List<Local> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Local computeNewCAOf(Local local, int amount, boolean isAdd) {
        if (isAdd) {
            local.increaseCaWith(amount);
        }else{
            local.decreaseCaWith(amount);
        }
        return update(local, local.getId());
    }
}