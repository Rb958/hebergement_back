package com.lsd.logement.service;

import com.lsd.logement.entity.SousCaisse;

public interface SousCaisseService extends GenericService<SousCaisse, Integer> {
    SousCaisse findByNameAndCaisse(String name, Integer caisseId);
}