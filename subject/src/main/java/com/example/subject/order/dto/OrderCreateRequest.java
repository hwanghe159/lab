package com.example.subject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateRequest {
    private final String orderNumber;
    private final String productName;
    private final Long memberId;
}
