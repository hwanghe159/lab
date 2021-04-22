package com.example.dddstart.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Money {

  private final int value;

  public Money add(Money money) {
    return new Money(this.value + money.value);
  }

  public Money multiply(int multiplier) {
    return new Money(this.value * multiplier);
  }
}
