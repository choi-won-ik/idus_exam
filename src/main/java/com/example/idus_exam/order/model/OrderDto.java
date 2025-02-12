package com.example.idus_exam.order.model;

import com.example.idus_exam.member.model.Gender;
import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.member.model.MemberDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

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
    public static class Detail{
        private MemberDto.MemberDetails member;
        private List<Details> details;
    }

    @Getter
    @Builder
    public static class Details{
        private String productName;
        private String orderNum;
    }
}
