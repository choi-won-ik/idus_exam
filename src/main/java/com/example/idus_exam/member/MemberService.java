package com.example.idus_exam.member;

import com.example.idus_exam.emailverify.EmailVerifyService;
import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerifyService emailVerifyService;

    @Transactional
    public void signup(MemberDto.SignupRequest request) {
        System.out.println(request.getPassword());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println(encodedPassword);
        Member member = Member.builder()
                .name(request.getName())
                .password(encodedPassword)
                .email(request.getEmail())
                .gender(request.getGender())
                .nickName(request.getName())
                .build();

        memberRepository.save(member);

        emailVerifyService.signup(member.getIdx(), member.getEmail());
    }

    @Transactional
    public void verify(String uuid) {
        Member member = emailVerifyService.verify(uuid);
        if(member != null) {
            member.verify();
            memberRepository.save(member);
        }
    }
}
