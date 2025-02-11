package com.example.day0211.member.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String nickName;
    private String password;
    @Unique
    private String email;

    @Enumerated(EnumType.STRING)  // Enum 값을 문자열로 저장 (권장)
    private Gender gender;
}
