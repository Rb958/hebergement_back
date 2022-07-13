package com.lsd.logement.controller;

import com.lsd.logement.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Article API")
public interface ArticleController {
    @ApiOperation("Add new data")
    public ArticleDTO save(@RequestBody ArticleDTO article);

    @ApiOperation("Find by Id")
    public ArticleDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<ArticleDTO> list();

    @ApiOperation("Pagination request")
    public Page<ArticleDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public ArticleDTO update(@RequestBody ArticleDTO dto, @PathVariable("id") Integer id);
}