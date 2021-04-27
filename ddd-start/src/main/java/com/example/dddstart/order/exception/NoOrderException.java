package com.example.dddstart.order.exception;

public class NoOrderException extends RuntimeException {

  public NoOrderException(Long id) {
    super(String.format("id %d에 해당하는 주문이 존재하지 않습니다.", id));
  }
}
