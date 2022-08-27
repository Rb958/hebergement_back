package com.lsd.logement.service.impl;

import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.service.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {
    private final BailService bailService;
    private final BookingService bookingService;
    private final LocalService localService;
    private final DepenseService depenseService;

    public StatServiceImpl(BailService bailService, BookingService bookingService, LocalService localService, DepenseService depenseService) {
        this.bailService = bailService;
        this.bookingService = bookingService;
        this.localService = localService;
        this.depenseService = depenseService;
    }

    @Override
    public Map<String, Object> getDashboardStat() {
        Map<String, Object> stats = new HashMap<>();
        int localSousContratBail = bailService.countAllAvailable();
        int localReserve = bookingService.countAllAvailable();
        int totalLocalMeuble = localService.countAllByCategory(CategorieEnum.MEUBLÉ);
        int totalLocalNonMeuble = localService.countAllByCategory(CategorieEnum.NON_MEUBLÉ);

        int meuble = Math.round(((float) localReserve / totalLocalMeuble) );
        int nonMeuble = Math.round(((float) localSousContratBail / totalLocalNonMeuble) * 100);

        long depenseMois = depenseService.countDepenseMois();
        long depenseAnnee = depenseService.countDepenseAnnee();
        stats.put("depenseMois", depenseMois);
        stats.put("depenseAnnee", depenseAnnee);
        stats.put("availableBails", localSousContratBail);
        stats.put("tom", meuble);
        stats.put("tonm", nonMeuble);
        return stats;
    }
}
