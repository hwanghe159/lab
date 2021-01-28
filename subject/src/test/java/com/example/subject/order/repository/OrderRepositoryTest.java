package com.example.subject.order.repository;

import com.example.subject.member.domain.Gender;
import com.example.subject.member.domain.Member;
import com.example.subject.member.repository.MemberRepository;
import com.example.subject.order.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

import static com.example.subject.fixture.MemberFixture.*;
import static com.example.subject.fixture.OrderFixture.TEST_ORDER_NAME;
import static com.example.subject.fixture.OrderFixture.TEST_ORDER_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Sql("/truncate.sql")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @DisplayName("단일 회원 주문 목록 조회가 가능해야 한다")
    @Test
    void findByMemberIdTest() {
        member = Member.builder()
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
        assertThat(orderRepository.findByMemberId(1L)).isNotEmpty();
    }

    @DisplayName("회원 엔티티로 주문을 조회할 수 있어야 한다")
    @Test
    void findByMemberTest() {
        member = Member.builder()
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
        assertThat(orderRepository.findByMember(member)).isNotEmpty();
    }

    @DisplayName("회원 번호로 마지막 주문을 조회할 수 있어야 한다")
    @Test
    void findLastOrderBy() {
        Member member1 = Member.builder()
                .name("name1")
                .nickname("nickname1")
                .password("password1")
                .phoneNumber("1")
                .email("1@gmail.com")
                .gender(Gender.MALE)
                .build();
        Member member2 = Member.builder()
                .name("name2")
                .nickname("nickname2")
                .password("password2")
                .phoneNumber("2")
                .email("2@gmail.com")
                .gender(Gender.MALE)
                .build();
        memberRepository.saveAll(Arrays.asList(member1, member2));

        Order order1 = new Order("1", "이름1", member1);
        orderRepository.save(order1);
        Order order2 = new Order("2", "이름2", member1);
        orderRepository.save(order2);
        Order order3 = new Order("3", "이름3", member2);
        orderRepository.save(order3);
        Order order4 = new Order("4", "이름4", member2);
        orderRepository.save(order4);

        Order lastOrderOfMemberOne = orderRepository.findLastOrderBy(1L);
        Order lastOrderOfMemberTwo = orderRepository.findLastOrderBy(2L);

        assertAll(
                () -> assertThat(lastOrderOfMemberOne.getOrderNumber()).isEqualTo("2"),
                () -> assertThat(lastOrderOfMemberTwo.getOrderNumber()).isEqualTo("4")
        );
    }
}