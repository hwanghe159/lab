package com.example.subject.order.domain;

import com.example.subject.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 12)
    private String orderNumber;

    private String productName;

    private LocalDateTime createdDate;

    @ManyToOne
    private Member member;

    public Order(String orderNumber, String productName, Member member) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.createdDate = LocalDateTime.now();
        this.member = member;
    }
}
