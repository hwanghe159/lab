package com.example.springdatajpa.case1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Case1LearningTest {

  /**
   * 부모(1) - 자식(N)
   * <p>연관관계의 주인 : 자식
   * <p>cascade = CascadeType.ALL
   */

  @Autowired
  private Parent1Service parent1Service;

  @DisplayName("부모에서 자식을 삭제할때 child.setParent(null)를 해줘야 연관이 끊어진다 (delete문은 안나감)")
  @Test
  void setParent() {
    Parent1 parent = parent1Service.saveParent("부모");
    Child1 child1 = parent1Service.addChild(parent.getId(), "자식1");
    parent1Service.addChild(parent.getId(), "자식2");

    parent1Service.deleteChild(parent.getId(), child1.getId());
    System.out.println("db 확인");
  }
}