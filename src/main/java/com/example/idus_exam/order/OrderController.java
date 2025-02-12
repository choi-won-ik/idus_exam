package com.example.idus_exam.order;

import com.example.idus_exam.order.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/add")
    public void add(@RequestBody OrderDto.OrderAdd dto) {
        orderService.add(dto);
    }

    @GetMapping("/list/{idx}")
    public List<OrderDto.SearchReq> list(@PathVariable("idx") Integer idx,int page, int size) {
        return orderService.list(Long.valueOf(idx),page, size);
    }


}