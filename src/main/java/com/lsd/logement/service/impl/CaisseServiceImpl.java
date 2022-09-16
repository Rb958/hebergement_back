package com.lsd.logement.service.impl;

import com.lsd.logement.dao.CaisseRepository;
import com.lsd.logement.dao.TransactionCaisseRepository;
import com.lsd.logement.entity.SousCaisse;
import com.lsd.logement.entity.TransactionCaisse;
import com.lsd.logement.entity.TransactionTypeEnum;
import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.StatutCaisse;
import com.lsd.logement.exception.GeneralBaseException;
import com.lsd.logement.exception.NotFoundMessage;
import com.lsd.logement.service.CaisseService;
import com.lsd.logement.service.SousCaisseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CaisseServiceImpl implements CaisseService {
    private final CaisseRepository repository;
    private final TransactionCaisseRepository transactionCaisseRepository;
    private final SousCaisseService sousCaisseService;

    public CaisseServiceImpl(CaisseRepository repository, TransactionCaisseRepository transactionCaisseRepository, SousCaisseService sousCaisseService) {
        this.repository = repository;
        this.transactionCaisseRepository = transactionCaisseRepository;
        this.sousCaisseService = sousCaisseService;
    }

    @Override
    public Caisse save(Caisse entity) {
        ZonedDateTime currenDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currenDateTime);
        entity.setLastUpdatedAt(currenDateTime);
        entity.setStatus(StatutCaisse.FERME);
        entity.setRef("CS-" + (new Date()).toInstant().toEpochMilli());
        entity.setSolde(0);
        List<SousCaisse> tmp = createSousCaisse(entity);
        entity.setSousCaisses(tmp);
        return repository.save(entity);
    }

    private List<SousCaisse> createSousCaisse(Caisse entity) {
        List<SousCaisse> sousCaisses = new ArrayList<>();

        SousCaisse momo = new SousCaisse();
        momo.setCaisse(entity);
        momo.setName("MTN Mobile Money");
        momo.setCreatedAt(ZonedDateTime.now());
        momo.setLastUpdatedAt(ZonedDateTime.now());
        sousCaisses.add(momo);

        SousCaisse om = new SousCaisse();
        om.setCaisse(entity);
        om.setName("Orange Money");
        om.setCreatedAt(ZonedDateTime.now());
        om.setLastUpdatedAt(ZonedDateTime.now());
        sousCaisses.add(om);

        SousCaisse esp = new SousCaisse();
        esp.setCaisse(entity);
        esp.setName("Espèces");
        esp.setCreatedAt(ZonedDateTime.now());
        esp.setLastUpdatedAt(ZonedDateTime.now());
        sousCaisses.add(esp);

        SousCaisse cred = new SousCaisse();
        cred.setCaisse(entity);
        cred.setName("Crédit");
        cred.setCreatedAt(ZonedDateTime.now());
        cred.setLastUpdatedAt(ZonedDateTime.now());
        sousCaisses.add(cred);

        SousCaisse cheq = new SousCaisse();
        cheq.setCaisse(entity);
        cheq.setName("Chèque");
        cheq.setCreatedAt(ZonedDateTime.now());
        cheq.setLastUpdatedAt(ZonedDateTime.now());
        sousCaisses.add(cheq);
        return sousCaisses;
    }

    @Override
    public List<Caisse> save(List<Caisse> entities) {
        return (List<Caisse>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Caisse> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Caisse> findAll() {
        return (List<Caisse>) repository.findAll();
    }

    @Override
    public Page<Caisse> findAll(Pageable pageable) {
        Page<Caisse> entityPage = repository.findAll(pageable);
        List<Caisse> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Caisse update(Caisse entity, Integer id) {
        Optional<Caisse> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            entity.setStatus(optional.get().getStatus());
            return save(entity);
        }
        return null;
    }

    public Caisse findOpened(int userId){
        Optional<Caisse> optional = repository.findOpenedCaisse(userId, StatutCaisse.OUVERT);
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        return optional.get();
    }

    @Override
    public Caisse findByUserId(Integer id) {
        Optional<Caisse> optional = repository.findByUser_Id(id);
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        return optional.get();
    }

    @Override
    public Caisse open(Caisse caisse) {
        Optional<Caisse> optional = repository.findById(caisse.getId());
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse tmpCaise = optional.get();
        tmpCaise.setSolde(caisse.getSolde());
        tmpCaise.setStatus(StatutCaisse.ATTENTE);
        return repository.save(tmpCaise);
    }

    @Override
    public Caisse validate(Integer id) {
        Optional<Caisse> optional = repository.findById(id);
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse newCaisse = optional.get();
        newCaisse.setStatus(StatutCaisse.OUVERT);
        return repository.save(newCaisse);
    }

    public Caisse pay(Payement payement, int userId){
        Optional<Caisse> optional = repository.findByUser_Id(userId);
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse caisse = optional.get();
        TransactionCaisse transaction = new TransactionCaisse();
        transaction.setAmount(payement.getAmount());
        transaction.setPaymentMethod(payement.getPaymentMethod());
        transaction.setCaisse(caisse);
        transactionCaisseRepository.save(transaction);
        switch(payement.getPaymentMethod()){
            case "MTN Mobile Money":
                SousCaisse momoSousCaisse = sousCaisseService.findByNameAndCaisse("MTN Mobile Money", caisse.getId());
                momoSousCaisse.add(payement.getAmount());
                sousCaisseService.update(momoSousCaisse, momoSousCaisse.getId());
                caisse.setSolde(caisse.getSolde() + payement.getAmount());
                break;
            case "Orange Money":
                SousCaisse omSousCaisse = sousCaisseService.findByNameAndCaisse("Orange Money", caisse.getId());
                omSousCaisse.add(payement.getAmount());
                sousCaisseService.update(omSousCaisse, omSousCaisse.getId());
                caisse.setSolde(caisse.getSolde() + payement.getAmount());
                break;
            case "Espèces":
                SousCaisse especeSousCaisse = sousCaisseService.findByNameAndCaisse("Espèces", caisse.getId());
                especeSousCaisse.add(payement.getAmount());
                sousCaisseService.update(especeSousCaisse, especeSousCaisse.getId());
                caisse.setSolde(caisse.getSolde() + payement.getAmount());
                break;
            case "Crédit":
                SousCaisse creditSousCaisse = sousCaisseService.findByNameAndCaisse("Crédit", caisse.getId());
                creditSousCaisse.add(payement.getAmount());
                sousCaisseService.update(creditSousCaisse, creditSousCaisse.getId());
                caisse.setSolde(caisse.getSolde() + payement.getAmount());
                break;
            case "Chèque":
                SousCaisse chequeSousCaisse = sousCaisseService.findByNameAndCaisse("Chèque", caisse.getId());
                chequeSousCaisse.add(payement.getAmount());
                sousCaisseService.update(chequeSousCaisse, chequeSousCaisse.getId());
                caisse.setSolde(caisse.getSolde() + payement.getAmount());
                break;
        }
        return repository.save(caisse);
    }

    @Override
    public Caisse closeRequest(Caisse caisse) {
        Optional<Caisse> caisseOptional = repository.findById(caisse.getId());
        if (!caisseOptional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse tmpCaisse = caisseOptional.get();
        tmpCaisse.setStatus(StatutCaisse.ATTENTE_FERMETURE);
        tmpCaisse.setLastClosing(ZonedDateTime.now());
        return repository.save(tmpCaisse);
    }

    @Override
    public Caisse close(Integer id) {
        Optional<Caisse> caisseOptional = repository.findById(id);
        if (!caisseOptional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse tmpCaisse = caisseOptional.get();
        tmpCaisse.setStatus(StatutCaisse.FERME);
        tmpCaisse.setLastClosing(ZonedDateTime.now());
        return repository.save(tmpCaisse);
    }

    @Override
    public boolean hasPrincipalCaisse() {
        return repository.existsByPrincipalIsTrue();
    }

    @Override
    public InputStream generatePdf(Integer id) throws Exception {
        Optional<Caisse> caisseOptional = repository.findById(id);
        if (!caisseOptional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }

        File inputHTML = ResourceUtils.getFile("classpath:journal_caisse.html");
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        String htmlString = document.html();
        StringBuilder tbody = new StringBuilder();
        tbody.append("<tbody>");
        Caisse caisse = caisseOptional.get();
        final int[] i = {1};
        caisse.getSousCaisses().forEach(sousCaisse -> {
            tbody.append("<tr>")
                    .append("<td>").append(i[0]).append("</td>")
                    .append("<td>").append(sousCaisse.getName()).append("</td>")
                    .append("<td>").append(sousCaisse.getTotal()).append("</td>")
                    .append("<td>").append(sousCaisse.getSoldeInit()).append("</td>")
                    .append("<td>").append(sousCaisse.getSoldeFin()).append("</td>")
                    .append("<td>").append(sousCaisse.getDiff()).append("</td>")
                    .append("</tr>");
            i[0]++;
        });
        tbody.append("</tbody>");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        htmlString = htmlString.replace("{table_content}", tbody.toString())
        .replace("{lastname}", caisse.getUser().getLastname())
        .replace("{firstname}", caisse.getUser().getFirstname())
        .replace("{solde}", String.valueOf(caisse.getSolde()))
        .replace("{lastOpening}", caisse.getLastOpening().format(format))
        .replace("{lastClosing}", caisse.getLastClosing().format(format));
        try(OutputStream outputStream = new FileOutputStream("journal-caisse_" + id + ".pdf")){
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(htmlString);
            renderer.layout();
            renderer.createPDF(outputStream);
            return new FileInputStream("journal-caisse_" + id + ".pdf");
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }



    @Override
    public void debitPrincipal(int montant) {
        Optional<Caisse> optional = repository.findPrincipal();
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse principal = optional.get();
        TransactionCaisse transactionCaisse = new TransactionCaisse();
        transactionCaisse.setAmount(montant);
        transactionCaisse.setType(TransactionTypeEnum.DEBIT);
        transactionCaisse.setCaisse(principal);
        transactionCaisse.setPaymentMethod("Espèces");
        transactionCaisseRepository.save(transactionCaisse);
        principal.setSolde(principal.getSolde() + montant);
    }

    @Override
    public void creditPrincipal(int montant) {
        Optional<Caisse> optional = repository.findPrincipal();
        if (!optional.isPresent()){
            throw new GeneralBaseException(NotFoundMessage.CAISSE_NOT_FOUND);
        }
        Caisse principal = optional.get();
        TransactionCaisse transactionCaisse = new TransactionCaisse();
        transactionCaisse.setAmount(montant);
        transactionCaisse.setType(TransactionTypeEnum.CREDIT);
        transactionCaisse.setCaisse(principal);
        transactionCaisse.setPaymentMethod("Espèces");
        transactionCaisseRepository.save(transactionCaisse);
        principal.setSolde(principal.getSolde() + montant);
    }
}