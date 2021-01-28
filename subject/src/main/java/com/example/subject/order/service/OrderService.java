package com.example.subject.order.service;

import com.example.subject.member.domain.Member;
import com.example.subject.member.exception.NoSuchMemberException;
import com.example.subject.member.repository.MemberRepository;
import com.example.subject.order.domain.Order;
import com.example.subject.order.dto.OrderCreateRequest;
import com.example.subject.order.dto.OrderResponse;
import com.example.subject.order.exception.OrderNumberDuplicationException;
import com.example.subject.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public List<OrderResponse> find(Long memberId) {
        List<Order> orders = orderRepository.findByMemberId(memberId);

        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public OrderResponse create(OrderCreateRequest request) {
        Long memberId = request.getMemberId();
        String orderNumber = request.getOrderNumber();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchMemberException(memberId));
        if (orderRepository.findByOrderNumber(orderNumber).isPresent()) {
            throw new OrderNumberDuplicationException(orderNumber);
        }

        Order order = new Order(request.getOrderNumber(), request.getProductName(), member);
        Order savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder);
    }
}
