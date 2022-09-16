package com.lsd.logement.dao;

import com.lsd.logement.entity.Media;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MediaRepository extends PagingAndSortingRepository<Media, Integer> {

    Optional<Media> findByRef(String ref);
}
