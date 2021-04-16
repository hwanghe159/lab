package com.example.dddstart.order.domain;

import java.util.List;

public class Order {

  private List<OrderLine> orderLines;
  private int totalAmounts;

  private void setOrderLines(List<OrderLine> orderLines) {
    verifyAtLeastOneOrMoreOrderLines(orderLines);
    this.orderLines = orderLines;
    calculateTotalAmounts();
  }

  private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
    if (orderLines == null || orderLines.isEmpty()) {
      throw new IllegalArgumentException("no OrderLine");
    }
  }

  private void calculateTotalAmounts() {
    this.totalAmounts = new Money(orderLines.stream()
        .mapToInt(x -> x.getAmounts().getValue()).sum());
  }
}