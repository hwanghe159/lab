package com.example.subject.order.repository;

import com.example.subject.member.domain.Member;
import com.example.subject.member.repository.MemberRepository;
import com.example.subject.order.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.example.subject.fixture.MemberFixture.*;
import static com.example.subject.fixture.OrderFixture.TEST_ORDER_NAME;
import static com.example.subject.fixture.OrderFixture.TEST_ORDER_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .name(TEST_MEMBER_NAME)
                .nickname(TEST_NICKNAME)
                .password(TEST_PASSWORD)
                .phoneNumber(TEST_PHONE_NUMBER)
                .email(TEST_EMAIL)
                .gender(TEST_GENDER)
                .build();
        Order order = new Order(TEST_ORDER_NUMBER, TEST_ORDER_NAME, member);
        memberRepository.save(member);
        orderRepository.save(order);
    }

    @DisplayName("단일 회원 주문 목록 조회가 가능해야 한다")
    @Test
    void findByMemberIdTest() {
        assertThat(orderRepository.findByMemberId(1L)).isNotEmpty();
    }
}