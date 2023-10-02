package com.example.springdatajpa.case4;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class Parent4Service {

  private final Parent4Repository parent1Repository;

  @Transactional(readOnly = true)
  public List<Child4> findChildrenByParentId(Long parentId) {
    return parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new)
        .getChildren();
  }

  public Parent4 saveParent(String value) {
    Parent4 parent = new Parent4();
    parent.setValue(value);
    return parent1Repository.save(parent);
  }

  public Child4 addChild(Long parentId, String value) {
    Parent4 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child4 child = new Child4();
    child.setValue(value);
    child.setParent(parent);
    parent.getChildren().add(child);
    return child;
  }

  public void deleteChild1(Long parentId, Long childId) {
    Parent4 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child4 child = parent.getChildren()
        .stream()
        .filter(c -> c.getId().equals(childId))
        .findFirst()
        .orElseThrow(RuntimeException::new);
//    child.setParent(null);
    parent.getChildren().remove(child);
  }

  public void deleteChild2(Long parentId, Long childId) {
    Parent4 parent = parent1Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child4 child = parent.getChildren()
        .stream()
        .filter(c -> c.getId().equals(childId))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    child.setParent(null);
    parent.getChildren().remove(child);
  }
}
