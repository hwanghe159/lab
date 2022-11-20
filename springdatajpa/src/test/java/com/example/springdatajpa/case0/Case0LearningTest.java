package com.example.springdatajpa.case0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Case0LearningTest {

  /**
   * 부모(1) - 자식(N)
   * <p>연관관계의 주인 : 지정안됨
   * <p>cascade, orphanRemoval 설정 없음
   * <p>JoinColumn 이름 설정은 부모쪽에서 해야 함. 자식쪽에서 하면 "부모에서의필드명+id" 컬럼이 추가로 생김(children_id)
   */

  @Autowired
  private ParentChildService parentChildService;

  @DisplayName("child.setParent(null) 또는 parent.getChildren().remove(child) 둘 중 하나만 해도 연관관계가 끊어짐")
  @Test
  void setParent() {
    Parent0 parent = parentChildService.saveParent("부모");
    Child0 child1 = parentChildService.addChild(parent.getId(), "자식1");
    Child0 child2 = parentChildService.addChild(parent.getId(), "자식2");

    parentChildService.deleteChild(parent.getId(), child1.getId());
    System.out.println("db 확인");
  }
}