package com.example.subject.order.controller;

import com.example.subject.order.dto.OrderResponse;
import com.example.subject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/members/{memberId}/orders")
    public ResponseEntity<List<OrderResponse>> find(@PathVariable Long memberId) {
        return ResponseEntity.ok(orderService.find(memberId));
    }
}
