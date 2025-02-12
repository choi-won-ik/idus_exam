package com.example.idus_exam.member.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignupRequest {
        @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")@Size(max = 20) @NotBlank
        private String name;
        @Size(max = 30) @NotBlank
        private String nickName;
        @NotBlank @Size(min=10)
        private String password;
        @Email(message = "email형식이 옳바르지 않습니다.")@NotBlank(message = "이메일을 입력해 주십시오.")@Size(max = 100)
        private String email;
        private Gender gender;
        @Size(max = 20)@NotBlank
        private String phone;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter
    @Builder
    public static class MemberDetails {
        private String name;
        private String nickName;
        private String email;
        private Gender gender;
        private String phone;
    }
}
