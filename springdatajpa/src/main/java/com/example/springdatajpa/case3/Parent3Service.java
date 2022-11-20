package com.example.springdatajpa.case3;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class Parent3Service {

  private final Parent3Repository parent1Repository;

  @Transactional(readOnly = true)
  public List<Child3> findChildrenByParentId(Long parentId) {
    return parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new)
        .getChildren();
  }

  public Parent3 saveParent(String value) {
    Parent3 parent = new Parent3();
    parent.setValue(value);
    return parent1Repository.save(parent);
  }

  public Child3 addChild(Long parentId, String value) {
    Parent3 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child3 child = new Child3();
    child.setValue(value);
    child.setParent(parent);
    parent.getChildren().add(child);
    return child;
  }

  public void deleteChild(Long parentId, Long childId) {
    Parent3 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child3 child = parent.getChildren()
        .stream()
        .filter(c -> c.getId().equals(childId))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    child.setParent(null);
    parent.getChildren().remove(child);
  }
}
