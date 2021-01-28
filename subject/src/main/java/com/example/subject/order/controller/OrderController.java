package com.example.subject.order.controller;

import com.example.subject.order.dto.OrderCreateRequest;
import com.example.subject.order.dto.OrderResponse;
import com.example.subject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/members/{memberId}/orders")
    public ResponseEntity<List<OrderResponse>> find(@PathVariable Long memberId) {
        return ResponseEntity.ok(orderService.find(memberId));
    }

    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity<Long> create(@PathVariable String memberId, @RequestBody OrderCreateRequest request) {
        OrderResponse orderResponse = orderService.create(request);
        return ResponseEntity.created(URI.create("/members/" + memberId + "/orders/" + orderResponse.getId()))
                .body(orderResponse.getId());
    }
}
