package com.example.transactional.service;

import com.example.transactional.domain.Article;
import com.example.transactional.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReadOnlyServiceTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReadOnlyService readOnlyService;

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article("제목1", "내용1"));
        articleRepository.save(new Article("제목2", "내용2"));
        articleRepository.save(new Article("제목3", "내용3"));
        articleRepository.save(new Article("제목4", "내용4"));
    }

    @DisplayName("읽기 전용 메서드에서 쓰기를 시도하는 경우 잘 저장되는지")
    @Test
    void createTest() {
        readOnlyService.create(new Article("제목5", "내용5"));

        List<Article> articles = readOnlyService.readAll();

        assertAll(
                () -> assertThat(articles).hasSize(5),
                () -> assertThat(articles.get(4).getTitle()).isEqualTo("제목5")
        );
    }
}