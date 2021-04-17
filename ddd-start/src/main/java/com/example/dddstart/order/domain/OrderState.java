package com.example.dddstart.order.domain;

public enum OrderState {
  PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERED, DELIVERY_COMPLETED, CANCELED;
}
