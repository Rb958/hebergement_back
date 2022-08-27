package com.lsd.logement.service.impl;

import com.lsd.logement.dao.SousCaisseRepository;
import com.lsd.logement.dto.SousCaisseDTO;
import com.lsd.logement.entity.SousCaisse;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.NotFoundMessage;
import com.lsd.logement.mapper.SousCaisseMapper;
import com.lsd.logement.service.SousCaisseService;
import org.mapstruct.factory.Mappers;
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
public class SousCaisseServiceImpl implements SousCaisseService {
    private final SousCaisseRepository repository;

    public SousCaisseServiceImpl(SousCaisseRepository repository) {
        this.repository = repository;
    }

    @Override
    public SousCaisse save(SousCaisse entity) {
        return repository.save(entity);
    }

    @Override
    public List<SousCaisse> save(List<SousCaisse> entities) {
        return (List<SousCaisse>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SousCaisse> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<SousCaisse> findAll() {
        return (List<SousCaisse>) repository.findAll();
    }

    @Override
    public Page<SousCaisse> findAll(Pageable pageable) {
        Page<SousCaisse> entityPage = repository.findAll(pageable);
        List<SousCaisse> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public SousCaisse update(SousCaisse entity, Integer id) {
        Optional<SousCaisse> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            entity.setCaisse(optional.get().getCaisse());
            return save(entity);
        }
        return null;
    }

    @Override
    public SousCaisse findByNameAndCaisse(String name, Integer caisseId) {
        Optional<SousCaisse> sousCaisseOptional = repository.findByNameAndCaisse_Id(name, caisseId);
        if (!sousCaisseOptional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        return sousCaisseOptional.get();
    }
}