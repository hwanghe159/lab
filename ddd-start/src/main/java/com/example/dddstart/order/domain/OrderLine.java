package com.example.dddstart.order.domain;

import com.example.dddstart.product.domain.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderLine {

  private final Product product;
  private final Money price;
  private final int quantity;
  private final Money amounts;

  public OrderLine(Product product, Money price, int quantity) {
    this.product = product;
    this.price = price;
    this.quantity = quantity;
    this.amounts = calculateAmounts();
  }

  private Money calculateAmounts() {
    return this.price.multiply(this.quantity);
  }
}
