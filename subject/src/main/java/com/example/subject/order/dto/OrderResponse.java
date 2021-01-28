package com.example.subject.order.dto;

import com.example.subject.member.dto.MemberResponse;
import com.example.subject.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String productName;
    private MemberResponse memberResponse;
    private LocalDateTime createdDate;

    public OrderResponse(Order order) {
        if (order == null) {
            return;
        }
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.productName = order.getProductName();
        this.createdDate = order.getCreatedDate();
        this.memberResponse = new MemberResponse(order.getMember());
    }
}
