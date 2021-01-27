package com.example.subject.order.repository;

import com.example.subject.fixture.OrderFixture;
import com.example.subject.member.domain.Member;
import com.example.subject.member.repository.MemberRepository;
import com.example.subject.order.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.example.subject.fixture.MemberFixture.*;
import static com.example.subject.fixture.OrderFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member(TEST_MEMBER_NAME, TEST_NICKNAME, TEST_PASSWORD, TEST_PHONE_NUMBER, TEST_EMAIL, TEST_GENDER);
        Order order = new Order(TEST_ORDER_NUMBER, TEST_ORDER_NAME, member);
        memberRepository.save(member);
        orderRepository.save(order);
    }

    @DisplayName("단일 회원 주문 목록 조회가 가능해야 한다")
    @Test
    void findByMemberIdTest() {
        List<Order> orders = orderRepository.findByMemberId(1L);
        System.out.println(orders);
        assertThat(orderRepository.findByMemberId(1L)).isNotEmpty();
    }
}