package com.lsd.logement.service;

import com.lsd.logement.entity.infra.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocalService extends GenericService<Local, Integer> {
    Page<Local> findAllAvailable(Pageable pageable);

    Local computeNewCAOf(Local local, int amount, boolean isAdd);
}