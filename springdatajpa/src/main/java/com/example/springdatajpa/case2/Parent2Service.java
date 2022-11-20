package com.example.springdatajpa.case2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class Parent2Service {

  private final Parent2Repository parent2Repository;

  public Parent2 saveParent(String value) {
    Parent2 parent = new Parent2();
    parent.setValue(value);
    return parent2Repository.save(parent);
  }

  public Child2 addChild(Long parentId, String value) {
    Parent2 parent = parent2Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child2 child = new Child2();
    child.setValue(value);
    child.setParent(parent);
    parent.getChildren().add(child);
    return child;
  }

  public void deleteChild(Long parentId, Long childId) {
    Parent2 parent = parent2Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child2 child = parent.getChildren()
        .stream()
        .filter(c -> c.getId().equals(childId))
        .findFirst()
        .orElseThrow(RuntimeException::new);
//    child.setParent(null);
    parent.getChildren().remove(child);
  }
}
