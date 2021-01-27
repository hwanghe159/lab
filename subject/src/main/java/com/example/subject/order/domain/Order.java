package com.example.subject.order.domain;

import com.example.subject.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ORDERS")
@NoArgsConstructor
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 12)
    private String orderNumber;

    private String name;

    private LocalDateTime createdDate;

    @ManyToOne
    private Member member;

    public Order(String orderNumber, String name, Member member) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.createdDate = LocalDateTime.now();
        this.member = member;
    }
}
