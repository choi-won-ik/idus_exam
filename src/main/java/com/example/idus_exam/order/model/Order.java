package com.example.idus_exam.order.model;

import com.example.idus_exam.member.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 12, nullable = false, unique = true)
    private String orderNum;
    @Column(length = 100, nullable = false)
    private String productName;

    @Column(nullable = false)
    private ZonedDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
}
