package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.ArticleController;
import com.lsd.logement.dto.ArticleDTO;
import com.lsd.logement.entity.stock.Article;
import com.lsd.logement.mapper.ArticleMapper;
import com.lsd.logement.service.ArticleService;
import okhttp3.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/article")
@RestController
public class ArticleControllerImpl implements ArticleController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    public ArticleControllerImpl(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO save(@RequestBody ArticleDTO articleDTO) {
        Article article = articleMapper.asEntity(articleDTO);
        return articleMapper.asDTO(articleService.save(article));
    }

    @Override
    @GetMapping("/{id}")
    public ArticleDTO findById(@PathVariable("id") Integer id) {
        Article article = articleService.findById(id).orElse(null);
        return articleMapper.asDTO(article);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        articleService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<ArticleDTO> list() {
        return articleMapper.asDTOList(articleService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<ArticleDTO> pageQuery(Pageable pageable) {
        Page<Article> articlePage = articleService.findAll(pageable);
        List<ArticleDTO> dtoList = articlePage
                .stream()
                .map(articleMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, articlePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public ArticleDTO update(@RequestBody ArticleDTO articleDTO, @PathVariable("id") Integer id) {
        Article article = articleMapper.asEntity(articleDTO);
        return articleMapper.asDTO(articleService.update(article, id));
    }
}