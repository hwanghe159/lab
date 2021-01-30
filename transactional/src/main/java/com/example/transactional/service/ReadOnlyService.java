package com.example.transactional.service;

import com.example.transactional.domain.Article;
import com.example.transactional.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadOnlyService {

    private final ArticleRepository articleRepository;

    public Article read(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    public void create(Article article) {
        articleRepository.save(article);
    }

    public void update(Long id, Article article) {
        Article savedArticle = articleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        savedArticle.setTitle(article.getTitle());
        savedArticle.setContent(article.getContent());
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }
}
