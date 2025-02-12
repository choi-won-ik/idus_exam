package com.example.idus_exam.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

public class OrderDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderAdd {
        private Long member_idx;
        @NotBlank @Size(min = 1, max = 100)
        private String productName;
    }


    @Getter
    @Builder
    public static class SearchReq{
        private String productName;
        private String orderNum;
        private ZonedDateTime paymentDate;
    }
}
