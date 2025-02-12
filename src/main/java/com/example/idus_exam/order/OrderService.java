package com.example.idus_exam.order;

import com.example.idus_exam.member.MemberRepository;
import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.member.model.MemberDto;
import com.example.idus_exam.order.model.Order;
import com.example.idus_exam.order.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void add(OrderDto.OrderAdd dto) {
        Optional<Member> member=memberRepository.findById(dto.getMember_idx());
        if (member.isPresent()) {
            orderRepository.save(
                    Order.builder()
                            .orderNum(UUID.randomUUID().toString().replaceAll("[^A-Z0-9]", "").substring(0, 12))
                            .productName(dto.getProductName())
                            .member(member.get())
                            .paymentDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                            .build()
            );
        }
    }
}
