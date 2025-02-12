package com.example.idus_exam.order;

import com.example.idus_exam.member.MemberRepository;
import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.order.model.Order;
import com.example.idus_exam.order.model.OrderDto;
import com.example.idus_exam.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public List<OrderDto.SearchReq> list(Long idx,int page, int size) {
        Optional<List<Object[]>> op=memberRepository.findByList(idx, PageRequest.of(page, size));
        List<OrderDto.SearchReq> list=new ArrayList<>();
        if (op.isPresent()) {
            List<Object[]> li=op.get();
            for (Object[] row:li) {
                ZonedDateTime zonedDateTime=TimeUtil.timeChange(row[2].toString());

                list.add(OrderDto.SearchReq.builder()
                                .orderNum(row[0].toString())
                                .productName(row[1].toString())
                                .paymentDate(zonedDateTime)
                        .build());
            }
            return list;
        }
        return null;
    }
}
