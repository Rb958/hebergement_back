package com.lsd.logement.service;

import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.reservation.Bail;

import java.util.Date;
import java.util.Map;

public interface BailService extends GenericService<Bail, Integer> {

    Bail save(Bail entity, Integer userId);

    Bail addPayment(Integer userId, Integer bailId, Payement payement);

    Bail removePayment(Integer bailId, Payement payement);

    Map<String, Object> bailStats();

    Bail cancelBail(Integer bailId);

    int countAllAvailable();

    int countAllDueDate(Date now);
}