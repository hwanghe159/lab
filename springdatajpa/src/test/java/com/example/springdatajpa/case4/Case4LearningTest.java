package com.example.springdatajpa.case4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Case4LearningTest {

  /**
   * 부모(1) - 자식(N)
   * <p>연관관계의 주인 : 지정안함
   * <p>부모 -> cascade = CascadeType.ALL, orphanRemoval=true
   * <p>자식 -> cascade = CascadeType.ALL
   */

  @Autowired
  private Parent4Service parent4Service;

  @DisplayName("child.setParent(null) 안하면 delete 문 2개 나가고, child가 다 삭제되면 parent조차도 삭제한다")
  @Test
  void setParent1() {
    Parent4 parent = parent4Service.saveParent("부모");
    Child4 child1 = parent4Service.addChild(parent.getId(), "자식1");
    Child4 child2 = parent4Service.addChild(parent.getId(), "자식2");
    Child4 child3 = parent4Service.addChild(parent.getId(), "자식3");

    parent4Service.deleteChild1(parent.getId(), child1.getId());
    System.out.println("db 확인");
  }

  @DisplayName("child.setParent(null), parent.getChildren().remove(child) 둘 다 해야 의도한대로 동작한다")
  @Test
  void setParent2() {
    Parent4 parent = parent4Service.saveParent("부모");
    Child4 child1 = parent4Service.addChild(parent.getId(), "자식1");
    Child4 child2 = parent4Service.addChild(parent.getId(), "자식2");

    parent4Service.deleteChild2(parent.getId(), child1.getId());
    System.out.println("db 확인");
  }
}