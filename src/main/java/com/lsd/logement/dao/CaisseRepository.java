package com.lsd.logement.dao;

import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.entity.finance.StatutCaisse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaisseRepository extends PagingAndSortingRepository<Caisse, Integer> {
    @Query("select c from Caisse c where c.user.id = :userId and c.status = :status")
    Optional<Caisse> findOpenedCaisse(@Param("userId") int userId, @Param("status")StatutCaisse caisse);

    Optional<Caisse> findByUser_Id(int id);

    boolean existsByPrincipalIsTrue();
}