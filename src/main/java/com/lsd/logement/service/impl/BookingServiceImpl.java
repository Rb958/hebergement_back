package com.lsd.logement.service.impl;

import com.lsd.logement.dao.BookingRepository;
import com.lsd.logement.dao.PayementRepository;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.infra.LocateState;
import com.lsd.logement.entity.reservation.Booking;
import com.lsd.logement.entity.reservation.BookingState;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.NotFoundMessage;
import com.lsd.logement.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final LocalService localService;
    private final LocataireParticulierService locatairesPartService;
    private final LocataireSocieteService locataireSocieteService;
    private final PayementRepository payementRepository;
    private final CaisseService caisseService;

    public BookingServiceImpl(BookingRepository repository, LocalService localService, LocataireParticulierService locatairesPartService, LocataireSocieteService locataireSocieteService, PayementRepository payementRepository, CaisseService caisseService) {
        this.repository = repository;
        this.localService = localService;
        this.locatairesPartService = locatairesPartService;
        this.locataireSocieteService = locataireSocieteService;
        this.payementRepository = payementRepository;
        this.caisseService = caisseService;
    }

    @Override
    public Booking save(Booking entity, Integer userId) {
        this.localService.checkLocal(entity.getLocal(), entity.getDateReservation());
        ZonedDateTime currentDate = ZonedDateTime.now();
        entity.setCreatedAt(currentDate);
        entity.setLastUpdatedAt(currentDate);
        entity.setNumReservation(genCode());
        entity.getPayements().forEach(payement -> payement.setBooking(entity));
        processPayment(entity, userId);
        processLocalChanges(entity, true, LocateState.OCCUPE);
        return repository.save(entity);
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

    private void processLocalChanges(Booking entity, boolean addCa, LocateState status) {
        Optional<Local> localOpt = localService.findById(entity.getLocal().getId());
        // Compute new Local CA
        if (localOpt.isPresent()) {
            Local local = localOpt.get();
            Payement payement = entity.getPayements().get(0);
            local = localService.computeNewCAOf(local, payement.getAmount(), addCa);
            local.setStatus(status);
            entity.setLocal(local);
            localService.update(local, local.getId());
        }
    }

    private void processPayment(Booking entity, Integer userId) {
        if (!entity.getPayements().isEmpty()){
            Payement payement = entity.getPayements().get(0);
            if (payement.getAmount() <= 0){
                entity.setPaymentStatus(PaymentStatus.IMPAYE);
            }else {
                int localAmountForPeriod = computTolatPrice(entity);
                int rest = localAmountForPeriod - payement.getAmount();
                entity.setPaidAmount(payement.getAmount());
                entity.setRestAmount(rest);
                entity.setTotalAmount(localAmountForPeriod);
                payement.setRest(rest);
                entity.setStatut(BookingState.CONFIRME);
                if (rest <= 0) {
                    payement.setLast(true);
                    entity.setPaymentStatus(PaymentStatus.PAYE);
                } else {
                    payement.setLast(false);
                    entity.setPaymentStatus(PaymentStatus.PARTIELLE);
                }
                caisseService.pay(payement, userId);
            }
            List<Payement> payments = new ArrayList<>();
            payments.add(payement);
            entity.setPayements(payments);
        } else {
            entity.setPaymentStatus(PaymentStatus.IMPAYE);
        }
    }

    private int computePaid(Booking entity) {
        Optional<Booking> optional = repository.findById(entity.getId());
        AtomicInteger totalAmount = new AtomicInteger(0);
        if (!optional.isPresent()) {
            throw new GeneralBaseException(NotFoundMessage.BOOKING_NOTFOUND);
        }
        List<Payement> payments = optional.get().getPayements();
        payments.forEach(payment -> totalAmount.addAndGet(payment.getAmount()));
        return totalAmount.get();
    }


    private int computTolatPrice(Booking booking){
        Optional<Local> localOpt = localService.findById(booking.getLocal().getId());
        if (!localOpt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.LOCAL_NOT_FOUND);
        }
        int localprice = localOpt.get().getPrix();
        return booking.getSejour() * localprice;
    }


    @Override
    public Booking save(Booking entity) {
        return null;
    }

    @Override
    public List<Booking> save(List<Booking> entities) {
        return (List<Booking>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return (List<Booking>) repository.findAll();
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        Page<Booking> entityPage = repository.findAll(pageable);
        List<Booking> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Booking update(Booking entity, Integer id) {
        Optional<Booking> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }

    @Override
    public Booking addPayment(Integer userId, Integer bookingId, Payement payement) {
        Optional<Booking> opt = repository.findById(bookingId);
        if (!opt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.BOOKING_NOTFOUND);
        }
        Booking booking = opt.get();
        int currentRest = booking.getRestAmount() - payement.getAmount();
        int currentPaidAmount = booking.getPaidAmount() + payement.getAmount();
        booking.setPaidAmount(currentPaidAmount);
        booking.setRestAmount(currentRest);
        if (currentRest <= 0){
            booking.setPaymentStatus(PaymentStatus.PAYE);
        }
        Local local = booking.getLocal();
        if (local != null){
            int currentCa = local.getCa() + payement.getAmount();
            local.setCa(currentCa);
            localService.update(local, local.getId());
        }
        payement.setBooking(booking);
        Payement newPayment = payementRepository.save(payement);
        caisseService.pay(newPayment, userId);
        return repository.save(booking);
    }

    @Override
    public Booking removePayment(Integer userId, Integer bookingId, Payement payement) {
        Optional<Booking> opt = repository.findById(bookingId);
        if (!opt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.BOOKING_NOTFOUND);
        }
        Booking booking = opt.get();
        Local local = booking.getLocal();
        if (local != null){
            int currentCa = local.getCa() - payement.getAmount();
            local.setCa(currentCa);
            localService.update(local, local.getId());
            int reste = (sumAmount(booking) + payement.getAmount()) - local.getPrix();
            payement.setRest(reste);
        }
        booking.getPayements().add(payement);
        return repository.save(booking);
    }

    @Override
    public Map<String, Object> bookingStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("closed", repository.countBookingWithStatus(BookingState.CLOTURER));
        stats.put("canceled", repository.countBookingWithStatus(BookingState.ANNULE));
        stats.put("confirmed", repository.countBookingWithStatus(BookingState.CONFIRME));
        stats.put("annonymous", repository.countBookingWithStatus(BookingState.ANONYMOUS));
        return stats;
    }

    @Override
    public Booking cancelBooking(Integer bookingId) {
        Optional<Booking> bookingOpt = repository.findById(bookingId);
        if (!bookingOpt.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.BOOKING_NOTFOUND);
        }
        Booking booking = bookingOpt.get();
        booking.setLastUpdatedAt(ZonedDateTime.now());
        booking.setStatut(BookingState.ANNULE);
        booking.setPaymentStatus(PaymentStatus.REMBOURSSÉ);
        processLocalChanges(booking, false, LocateState.LIBRE);
        return repository.save(booking);
    }

    @Override
    public int countAllAvailable() {
        return repository.countAllByValiditeGreaterThanEqualAndStatutIsNot(new Date(), BookingState.CLOTURER);
    }

    private int sumAmount(Booking booking){
        AtomicInteger amount = new AtomicInteger();
        booking.getPayements().forEach(payement -> amount.addAndGet(payement.getAmount()));
        return amount.get();
    }
}