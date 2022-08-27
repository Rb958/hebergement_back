package com.lsd.logement.service;

import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.model.LocalAvailableSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface LocalService extends GenericService<Local, Integer> {
    Page<Local> findAllAvailable(Pageable pageable);

    Local computeNewCAOf(Local local, int amount, boolean isAdd);

    List<Local> findLocalAvailable(LocalAvailableSearch search);

    List<Local> findLocalAvailableNonMeuble(LocalAvailableSearch search);

    int countAllByCategory(CategorieEnum categorie);

    void checkLocal(Local local, Date startDate);
}