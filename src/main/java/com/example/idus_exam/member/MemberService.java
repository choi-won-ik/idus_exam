package com.example.idus_exam.member;

import com.example.idus_exam.emailverify.EmailVerifyService;
import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.member.model.MemberDto;
import com.example.idus_exam.order.OrderRepository;
import com.example.idus_exam.order.model.Order;
import com.example.idus_exam.order.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerifyService emailVerifyService;
    private final OrderRepository orderRepository;

    @Transactional
    public void signup(MemberDto.SignupRequest request) {
        System.out.println(request.getPhone());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println(encodedPassword);
        Member member = Member.builder()
                .name(request.getName())
                .password(encodedPassword)
                .email(request.getEmail())
                .gender(request.getGender())
                .nickName(request.getName())
                .phone(Long.parseLong(request.getPhone()))
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username+" 는 없는 아이디 입니다."));
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberDetails detail(Long idx) {

        Optional<Member> member=memberRepository.findById(idx);
        if (member.isPresent()) {
            return MemberDto.MemberDetails.builder()
                    .phone(String.valueOf(member.get().getPhone()))
                    .name(member.get().getName())
                    .email(member.get().getEmail())
                    .name(member.get().getNickName())
                    .gender(member.get().getGender())
                    .nickName(member.get().getNickName())
                    .build();




        }
        return null;
    }
}
