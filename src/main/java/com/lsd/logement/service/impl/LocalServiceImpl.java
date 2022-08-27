package com.lsd.logement.service.impl;

import com.lsd.logement.dao.BookingRepository;
import com.lsd.logement.dao.LocalRepository;
import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.infra.LocateState;
import com.lsd.logement.entity.infra.TypeLocal;
import com.lsd.logement.entity.reservation.BookingState;
import com.lsd.logement.exception.ConstraintsMessage;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.NotFoundMessage;
import com.lsd.logement.model.LocalAvailableSearch;
import com.lsd.logement.service.LocalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocalServiceImpl implements LocalService {
    private final LocalRepository repository;
    private final BookingRepository bookingRepository;

    public LocalServiceImpl(LocalRepository repository, BookingRepository bookingRepository) {
        this.repository = repository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Local save(Local entity) {
        ZonedDateTime currentDate = ZonedDateTime.now();
        entity.setCreatedAt(currentDate);
        entity.setLastUpdatedAt(currentDate);
        entity.setStatus(LocateState.LIBRE);
        if (entity.getCategorie() == CategorieEnum.NON_MEUBLÉ){
            entity.setImmobilisations(new ArrayList<>());
            entity.setTypePrix("MOIS");
        }else{
            entity.setTypePrix("JOURS");
        }
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

    public List<Local> findLocalAvailable(LocalAvailableSearch search){
        return bookingRepository.findLocalAvailable(
                TypeLocal.valueOf(search.getTypeLocal()),
                search.getStartDate(),
                CategorieEnum.MEUBLÉ,
                BookingState.CLOTURER
        );
    }

    @Override
    public List<Local> findLocalAvailableNonMeuble(LocalAvailableSearch search) {
        return bookingRepository.findLocalAvailable(
                TypeLocal.valueOf(search.getTypeLocal()),
                search.getStartDate(),
                CategorieEnum.NON_MEUBLÉ,
                BookingState.CLOTURER
        );
    }

    @Override
    public int countAllByCategory(CategorieEnum categorie) {
        return (int) repository.countAllByCategorie(categorie);
    }

    @Override
    public void checkLocal(Local local, Date startDate) {
        Optional<Local> optional = repository.findById(local.getId());
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.LOCAL_NOT_FOUND);
        }
        if (!isFree(local.getId(), startDate)){
            throw new GeneralBaseException(ConstraintsMessage.LOCAL_IS_NOT_FREE);
        }
    }

    private boolean isFree(Integer id, Date startDate) {
        return repository.localIsFree(id, startDate, BookingState.CLOTURER);
    }
}