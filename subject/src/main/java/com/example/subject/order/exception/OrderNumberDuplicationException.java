package com.example.subject.order.exception;

public class OrderNumberDuplicationException extends RuntimeException {
    public OrderNumberDuplicationException(String orderNumber) {
        super(String.format("주문 번호가 중복됩니다. 입력한 주문 번호: %s", orderNumber));
    }
}
