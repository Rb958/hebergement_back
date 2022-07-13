package com.lsd.logement.service.impl;

import com.lsd.logement.dao.StockRepository;
import com.lsd.logement.entity.stock.Stock;
import com.lsd.logement.service.StockService;
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
public class StockServiceImpl implements StockService {
    private final StockRepository repository;

    public StockServiceImpl(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stock save(Stock entity) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currentDateTime);
        entity.setLastUpdatedAt(currentDateTime);
        return repository.save(entity);
    }

    @Override
    public List<Stock> save(List<Stock> entities) {
        return (List<Stock>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Stock> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Stock> findAll() {
        return (List<Stock>) repository.findAll();
    }

    @Override
    public Page<Stock> findAll(Pageable pageable) {
        Page<Stock> entityPage = repository.findAll(pageable);
        List<Stock> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Stock update(Stock entity, Integer id) {
        Optional<Stock> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }
}