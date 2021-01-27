package com.example.subject.order.dto;

import com.example.subject.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String name;
    private LocalDateTime createdDate;

    public OrderResponse(Order order){
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.name = order.getName();
        this.createdDate = order.getCreatedDate();
    }
}
