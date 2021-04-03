package com.example.webclient;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class CoffeeRepository {

  private final Map<String, Coffee> coffeeMap = new HashMap<>();

  @PostConstruct
  public void init() {
    coffeeMap.put("latte", new Coffee("latte", 1500));
    coffeeMap.put("mocha", new Coffee("mocha", 1600));
    coffeeMap.put("americano", new Coffee("americano", 900));
  }

  public int getPriceByName(String name) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return coffeeMap.get(name).getPrice();
  }
}
