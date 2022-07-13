package com.lsd.logement.service.impl;

import com.lsd.logement.dao.ArticleRepository;
import com.lsd.logement.entity.stock.Article;
import com.lsd.logement.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;

    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Article save(Article entity) {
        return repository.save(entity);
    }

    @Override
    public List<Article> save(List<Article> entities) {
        return (List<Article>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Article> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return (List<Article>) repository.findAll();
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        Page<Article> entityPage = repository.findAll(pageable);
        List<Article> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Article update(Article entity, Integer id) {
        Optional<Article> optional = findById(id) );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}