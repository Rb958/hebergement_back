package com.lsd.logement.service;

import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.entity.finance.Payement;

import java.io.InputStream;

public interface CaisseService extends GenericService<Caisse, Integer> {
    Caisse findOpened(int userId);

    Caisse findByUserId(Integer id);

    Caisse open(Caisse caisse);

    Caisse validate(Integer id);

    Caisse pay(Payement payement, int userId);

    Caisse closeRequest(Caisse caisse);

    Caisse close(Integer id);

    boolean hasPrincipalCaisse();

    InputStream generatePdf(Integer id) throws Exception;

    void debitPrincipal(int montant);

    void creditPrincipal(int montant);
}