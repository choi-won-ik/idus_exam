package com.example.idus_exam.member;

import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.member.model.MemberDto;
import com.example.idus_exam.order.model.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public void signup(@Valid @RequestBody MemberDto.SignupRequest request) {
        memberService.signup(request);
    }

    @GetMapping("/verify")
    public void verify(String uuid) {
        memberService.verify(uuid);
    }

    @GetMapping("/detail/{idx}")
    public MemberDto.MemberDetails detail(@PathVariable("idx") int idx) {
        return memberService.detail(Long.valueOf(idx));
    }
}