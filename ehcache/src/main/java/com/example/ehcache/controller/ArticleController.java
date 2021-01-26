package com.example.ehcache.controller;

import com.example.ehcache.dto.ArticleCreateRequest;
import com.example.ehcache.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public Long create(@RequestBody ArticleCreateRequest request) {

    }

}
