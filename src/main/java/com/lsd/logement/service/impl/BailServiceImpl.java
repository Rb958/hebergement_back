package com.lsd.logement.service.impl;

import com.lsd.logement.dao.BailRepository;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.infra.LocateState;
import com.lsd.logement.entity.reservation.Bail;
import com.lsd.logement.entity.reservation.BookingState;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.NotFoundMessage;
import com.lsd.logement.service.BailService;
import com.lsd.logement.service.CaisseService;
import com.lsd.logement.service.LocalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
public class BailServiceImpl implements BailService {
    private final BailRepository repository;
    private final LocalService localService;
    private final CaisseService caisseService;

    public BailServiceImpl(BailRepository repository, LocalService localService, CaisseService caisseService) {
        this.repository = repository;
        this.localService = localService;
        this.caisseService = caisseService;
    }

    @Override
    public Bail save(Bail entity, Integer userId) {
        this.localService.checkLocal(entity.getLocal(), entity.getDateEntre());
        ZonedDateTime currentDate = ZonedDateTime.now();
        entity.setCreatedAt(currentDate);
        entity.setLastUpdatedAt(currentDate);
        entity.setNumReservation(genCode());
        entity.getPayements().forEach(payement -> {
            payement.setBail(entity);
            payement.setBooking(null);
        });
        processPayment(entity, userId);
        processLocalChanges(entity, true, LocateState.OCCUPE);
        return repository.save(entity);
    }

    private void computSejour(Bail entity) {
        int sejour = 0;
        if (entity.getDateEntre().before(entity.getValidite())){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate dateEntre = LocalDate.parse(dateFormat.format(entity.getDateEntre()));
            LocalDate dateFin = LocalDate.parse(dateFormat.format(entity.getValidite()));
            Period period = Period.between(
                    dateEntre.withDayOfMonth(1),
                    dateFin.withDayOfMonth(1)
            );
            sejour = period.getMonths();
            entity.setSejour(sejour);
        }else{
            throw new GeneralBaseException(600, "Les date d'entrée de sortie sont incoherentes");
        }
    }

    @Override
    public Bail save(Bail entity) {
        return null;
    }

    @Override
    public List<Bail> save(List<Bail> entities) {
        return (List<Bail>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Bail> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Bail> findAll() {
        return (List<Bail>) repository.findAll();
    }

    @Override
    public Page<Bail> findAll(Pageable pageable) {
        Page<Bail> entityPage = repository.findAll(pageable);
        List<Bail> entities = entityPage.getContent().stream()
                .peek(bail -> {
                    if (bail.getStatut() != BookingState.CLOTURER && bail.getStatut() != BookingState.ANNULE) {
                        Date endDate = bail.getValidite();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        int echeance = Period.between(LocalDate.now(), LocalDate.parse(formatter.format(endDate))).getDays();
                        bail.setEcheance(echeance);
                    }
                })
                .collect(Collectors.toList());
        repository.saveAll(entities);
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Bail update(Bail entity, Integer id) {
        Optional<Bail> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }

    @Override
    public Bail addPayment(Integer bailId, Payement payement) {
        Optional<Bail> opt = repository.findById(bailId);
        if (!opt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.BOOKING_NOTFOUND);
        }
        Bail bail = opt.get();
        bail.getPayements().add(payement);
        Local local = bail.getLocal();
        if (local != null){
            int currentCa = local.getCa() + payement.getAmount();
            local.setCa(currentCa);
            localService.update(local, local.getId());
        }
        return repository.save(bail);
    }

    @Override
    public Bail removePayment(Integer bailId, Payement payement) {
        Optional<Bail> opt = repository.findById(bailId);
        if (!opt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.BAIL_NOT_FOUND);
        }
        Bail bail = opt.get();
        Local local = bail.getLocal();
        if (local != null){
            int currentCa = local.getCa() - payement.getAmount();
            local.setCa(currentCa);
            localService.update(local, local.getId());
            int reste = (sumAmount(bail) + payement.getAmount()) - local.getPrix();
            payement.setRest(reste);
        }
        bail.getPayements().add(payement);
        return repository.save(bail);
    }

    @Override
    public Map<String, Object> bailStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("closed", repository.countBailWithStatus(BookingState.CLOTURER));
        stats.put("canceled", repository.countBailWithStatus(BookingState.ANNULE));
        stats.put("confirmed", repository.countBailWithStatus(BookingState.CONFIRME));
        stats.put("annonymous", repository.countBailWithStatus(BookingState.ANONYMOUS));
        return stats;
    }

    @Override
    public Bail cancelBail(Integer bailId) {
        Optional<Bail> bailOpt = repository.findById(bailId);
        if (!bailOpt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.BAIL_NOT_FOUND);
        }
        Bail bails = bailOpt.get();
        bails.setLastUpdatedAt(ZonedDateTime.now());
        bails.setStatut(BookingState.ANNULE);
        bails.setPaymentStatus(PaymentStatus.REMBOURSSÉ);
        processLocalChanges(bails, false, LocateState.LIBRE);
        return repository.save(bails);
    }

    @Override
    public int countAllAvailable() {
        return repository.countAllByValiditeGreaterThanEqualAndStatutIsNot(new Date(), BookingState.CLOTURER);
    }

    @Override
    public int countAllDueDate(Date now) {
        return repository.countAllDueDate(now);
    }

    private String genCode() {
        ZonedDateTime currentDate = ZonedDateTime.now();
        Random rnd = new Random();
        return "BK" +
                currentDate.getYear() +
                currentDate.getMonth() +
                currentDate.getDayOfMonth() +
                rnd.nextInt(999);
    }

    private void processLocalChanges(Bail entity, boolean addCa, LocateState status) {
        Optional<Local> localOpt = localService.findById(entity.getLocal().getId());
        // Compute new Local CA
        if (localOpt.isPresent()) {
            Local local = localOpt.get();
            Payement payement = entity.getPayements().get(0);
            final Local tmpLocal = localService.computeNewCAOf(local, payement.getAmount(), addCa);
            tmpLocal.setStatus(status);
            entity.setLocal(tmpLocal);
            localService.update(tmpLocal, tmpLocal.getId());
        }
    }

    private void processPayment(Bail entity, Integer userId) {
        if (!entity.getPayements().isEmpty()){
            Payement payement = entity.getPayements().get(0);
            if (payement.getAmount() <= 0){
                entity.setPaymentStatus(PaymentStatus.IMPAYE);
            }else {
                int rest = computTolatPrice(entity) - payement.getAmount();
                payement.setRest(rest);
                entity.setStatut(BookingState.CONFIRME);
                if (rest <= 0) {
                    payement.setLast(true);
                    entity.setPaymentStatus(PaymentStatus.PAYE);
                } else {
                    payement.setLast(false);
                    entity.setPaymentStatus(PaymentStatus.PARTIELLE);
                }
                new Thread(() -> caisseService.pay(payement, userId)).start();
            }
            List<Payement> payments = new ArrayList<>();
            payments.add(payement);
            entity.setPayements(payments);
        } else {
            entity.setPaymentStatus(PaymentStatus.IMPAYE);
        }
    }


    private int computTolatPrice(Bail bail){
        Optional<Local> localOpt = localService.findById(bail.getLocal().getId());
        if (!localOpt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.LOCAL_NOT_FOUND);
        }
        int localprice = localOpt.get().getPrix();
        return bail.getSejour() * localprice;
    }

    private int sumAmount(Bail bail){
        AtomicInteger amount = new AtomicInteger();
        bail.getPayements().forEach(payement -> amount.addAndGet(payement.getAmount()));
        return amount.get();
    }
}