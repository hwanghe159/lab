package com.example.webclient;

public class Coffee {

  private final String name;

  private final Integer price;

  public Coffee(String name, Integer price) {
    this.name = name;
    this.price = price;
  }

  public Integer getPrice() {
    return price;
  }
}
