package com.example.day0211.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.Length;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.ZonedDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
}
