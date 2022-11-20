package com.example.springdatajpa.case1;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class Parent1Service {

  private final Parent1Repository parent1Repository;

  @Transactional(readOnly = true)
  public List<Child1> findChildrenByParentId(Long parentId) {
    return parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new)
        .getChildren();
  }

  public Parent1 saveParent(String value) {
    Parent1 parent = new Parent1();
    parent.setValue(value);
    return parent1Repository.save(parent);
  }

  public Child1 addChild(Long parentId, String value) {
    Parent1 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child1 child = new Child1();
    child.setValue(value);
    child.setParent(parent);
    parent.getChildren().add(child);
    return child;
  }

  public void deleteChild(Long parentId, Long childId) {
    Parent1 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child1 child = parent.getChildren()
        .stream()
        .filter(c -> c.getId().equals(childId))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    child.setParent(null);
    parent.getChildren().remove(child);
  }
}
