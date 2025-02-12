package com.example.idus_exam.member;

import com.example.idus_exam.member.model.Member;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);


    @Query(value = "SELECT o.order_num, o.product_name, o.payment_date " +
            "FROM member m " +
            "LEFT JOIN orders o ON m.idx = o.member_idx " +
            "WHERE m.idx = :idx",
            countQuery = "SELECT COUNT(*) FROM orders o WHERE o.member_idx = :idx",
            nativeQuery = true)
    Optional<List<Object[]>> findByList(Long idx, PageRequest pageable);
}