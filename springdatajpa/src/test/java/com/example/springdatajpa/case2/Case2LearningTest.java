package com.example.springdatajpa.case2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Case2LearningTest {

  /**
   * 부모(1) - 자식(N)
   * <p>연관관계의 주인 : 자식
   * <p>부모 -> orphanRemoval = true, cascade = CascadeType.ALL
   */

  @Autowired
  private Parent2Service parent2Service;

  @DisplayName("부모에서 자식을 삭제할때 child.setParent(null)를 안하고 parent.getChildren().remove(..) 만 해도 hard delete 해버린다")
  @Test
  void setParent() {
    Parent2 parent = parent2Service.saveParent("부모");
    Child2 child1 = parent2Service.addChild(parent.getId(), "자식1");
    Child2 child2 = parent2Service.addChild(parent.getId(), "자식2");

    parent2Service.deleteChild(parent.getId(), child1.getId());
    System.out.println("db 확인");
  }
}