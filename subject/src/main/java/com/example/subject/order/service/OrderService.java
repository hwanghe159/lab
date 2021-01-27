package com.example.subject.order.service;

import com.example.subject.order.domain.Order;
import com.example.subject.order.dto.OrderResponse;
import com.example.subject.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderResponse> find(Long memberId) {
        List<Order> orders = orderRepository.findByMemberId(memberId);

        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }
}
