package com.example.idus_exam.member;

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
    }
}
