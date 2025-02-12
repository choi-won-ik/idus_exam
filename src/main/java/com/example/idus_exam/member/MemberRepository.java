package com.example.idus_exam.member;

import com.example.idus_exam.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query(value = "SELECT m.nick_name, m.name, o.product_name " +
            "FROM member m " +
            "LEFT JOIN orders o ON m.idx = o.member_idx " +
            "WHERE m.idx = :idx", nativeQuery = true)
    List<Object[]> test(@Param("idx") Long idx);
}