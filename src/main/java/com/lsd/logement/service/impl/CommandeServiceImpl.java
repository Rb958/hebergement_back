package com.lsd.logement.service.impl;

import com.lsd.logement.dao.CommandeRepository;
import com.lsd.logement.entity.stock.Commande;
import com.lsd.logement.entity.stock.LigneCommande;
import com.lsd.logement.entity.stock.StatutCommande;
import com.lsd.logement.service.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository repository;

    public CommandeServiceImpl(CommandeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Commande save(Commande entity) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        entity.setCreatedAt(currentDateTime);
        entity.setLastUpdatedAt(currentDateTime);
        entity.setStatut(StatutCommande.INITIE);
        entity.getLigneCommande().forEach(ligneCommande -> {
            ligneCommande.setCreatedAt(currentDateTime);
            ligneCommande.setLastUpdatedAt(currentDateTime);
            ligneCommande.setCommande(entity);
        });
        int prixTotal = getPrixTotal(entity);
        entity.setPrixTotal(prixTotal);
        return repository.save(entity);
    }

    private int getPrixTotal(Commande entity) {
        int prixTotal = 0;
        for (LigneCommande ligne : entity.getLigneCommande()){
            assert ligne.getQte() >= 0 && ligne.getPrixUnitaire() >= 0;
            prixTotal += (ligne.getPrixUnitaire() * ligne.getQte() );
        }
        return prixTotal;
    }

    @Override
    public List<Commande> save(List<Commande> entities) {
        return (List<Commande>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Commande> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Commande> findAll() {
        return (List<Commande>) repository.findAll();
    }

    @Override
    public Page<Commande> findAll(Pageable pageable) {
        Page<Commande> entityPage = repository.findAll(pageable);
        List<Commande> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Commande update(Commande entity, Integer id) {
        Optional<Commande> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setCreatedAt(optional.get().getCreatedAt());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            entity.setLigneCommande(optional.get().getLigneCommande());
            return save(entity);
        }
        return null;
    }
}