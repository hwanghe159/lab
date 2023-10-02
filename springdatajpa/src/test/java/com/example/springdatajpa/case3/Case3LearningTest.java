package com.example.springdatajpa.case3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Case3LearningTest {

  /**
   * 부모(1) - 자식(N)
   * <p>연관관계의 주인 : 지정안함
   * <p>부모 -> cascade = CascadeType.ALL
   * <p>자식 -> cascade = CascadeType.ALL
   */

  @Autowired
  private Parent3Service parent3Service;

  @DisplayName("child.setParent(null) 또는 parent.getChildren().remove(child) 하나만 해도 연관관계는 끊어지고 delete문은 안나감")
  @Test
  void setParent() {
    Parent3 parent = parent3Service.saveParent("부모");
    Child3 child1 = parent3Service.addChild(parent.getId(), "자식1");
    Child3 child2 = parent3Service.addChild(parent.getId(), "자식2");

    parent3Service.deleteChild(parent.getId(), child1.getId());
    System.out.println("db 확인");
  }
}