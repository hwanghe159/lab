package com.example.springdatajpa.case0;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParentChildService {

  private final Parent0Repository parent0Repository;
  private final Child0Repository child0Repository;

  @Transactional(readOnly = true)
  public List<Child0> findChildrenByParentId(Long parentId) {
    return parent0Repository.findById(parentId)
        .orElseThrow(RuntimeException::new)
        .getChildren();
  }

  public Parent0 saveParent(String value) {
    Parent0 parent = new Parent0();
    parent.setValue(value);
    return parent0Repository.save(parent);
  }

  public Child0 addChild(Long parentId, String value) {
    Parent0 parent = parent0Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child0 child = new Child0();
    child.setValue(value);
    child.setParent(parent);
    child0Repository.save(child);
    parent.getChildren().add(child);
    return child;
  }

  public void deleteChild(Long parentId, Long childId) {
    Parent0 parent = parent0Repository.findById(parentId)
        .orElseThrow(RuntimeException::new);
    Child0 child = parent.getChildren()
        .stream()
        .filter(c -> c.getId().equals(childId))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    child.setParent(null);
    parent.getChildren().remove(child);
  }
}
