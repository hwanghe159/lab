package com.example.springdatajpa.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleRepositoryTest {

  @Autowired
  private ArticleRepository articleRepository;

  @BeforeEach
  void setUp() {
    articleRepository.save(new Article("제목", "내용"));
  }

  @Test
  void dirtyCheck() {
    Article article = articleRepository.findById(1L)
        .orElseThrow(NoSuchElementException::new);

    Article newArticle = new Article(article.getId(), article.getTitle(), article.getContent());
    newArticle.setTitle("수정된제목");
    articleRepository.flush();

    Article savedArticle = articleRepository.findById(1L)
        .orElseThrow(NoSuchElementException::new);

    assertAll(
        () -> assertThat(articleRepository.findAll()).hasSize(1),
        () -> assertThat(savedArticle.getTitle()).isEqualTo("제목")
    );
  }
}