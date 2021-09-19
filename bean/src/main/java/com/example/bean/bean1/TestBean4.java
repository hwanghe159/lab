package com.example.bean.bean1;

import org.springframework.stereotype.Component;

@Component
public class TestBean4 {

  public TestBean4() {
    System.out.println("TestBean4 생성 완료");
  }

  public void method() {
    System.out.println("TestBean4의 메서드 실행");
  }
}
