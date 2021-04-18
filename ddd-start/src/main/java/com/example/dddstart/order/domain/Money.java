package com.example.dddstart.order.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Money {

  private final int value;

  public Money add(Money money) {
    return new Money(this.value + money.value);
  }

  public Money multiply(int multiplier) {
    return new Money(this.value * multiplier);
  }
}
