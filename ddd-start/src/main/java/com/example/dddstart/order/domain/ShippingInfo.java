package com.example.dddstart.order.domain;

import lombok.Getter;

@Getter
public class ShippingInfo {

  private Receiver receiver;
  private Address address;
}
