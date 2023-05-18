package com.example.profiler.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @GetMapping("/test/{str}")
  public ResponseEntity<String> test(@PathVariable String str) {
    String result = "하하";
    List<String> list = new ArrayList<>();
    while (true) {
      result = result + "하";
      list.add(result);
    }
  }
}
