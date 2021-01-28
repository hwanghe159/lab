package com.example.subject.order.repository;

import com.example.subject.member.domain.Member;
import com.example.subject.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberId(Long memberId);

    List<Order> findByMember(Member member);

    Optional<Order> findByOrderNumber(String orderNumber);

    @Query(value = "SELECT * FROM ORDERS WHERE MEMBER_ID = ?1 ORDER BY CREATED_DATE DESC LIMIT 1", nativeQuery = true)
    Order findLastOrderBy(Long memberId);
}
