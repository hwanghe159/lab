package com.example.transactional.service;

import com.example.transactional.domain.Article;
import com.example.transactional.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WriteService {

    private final ArticleRepository articleRepository;

    public Article read(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
