package com.example.idus_exam.order;

import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findByMember(Member member);
}
