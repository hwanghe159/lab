package com.example.bean.bean2;

import com.example.bean.bean1.TestBean4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestBean3 {

  @Autowired
  private TestBean4 testBean4;

  public TestBean3() {
    System.out.println("TestBean3 생성 완료");
    testBean4.method();
  }
}
