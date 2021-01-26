package com.example.ehcache.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleCreateRequest {
    private final String title;
    private final String content;
}
