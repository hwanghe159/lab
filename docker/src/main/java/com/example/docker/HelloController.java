package com.example.docker;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String home() {
    int mb = 1024 * 1024;
    MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
    long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
    long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
    return "hello world.\nInitial Memory (xms) : " + xms + "mb\nMax Memory (xmx) : " + xmx + "mb";
  }

  @GetMapping("/test")
  public String test() {
    List<String> list = new ArrayList<>();
    int i = 0;
    while (true) {
      list.add("haha" + i);
      i++;
    }
  }
}