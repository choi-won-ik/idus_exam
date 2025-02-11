package com.example.idus_exam.config.security.filter;

import com.example.idus_exam.member.model.Member;
import com.example.idus_exam.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtFilter 실행됐다.");
        Cookie[] cookies = request.getCookies();

        String jwtToken = null;
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("ATOKEN")) {
                    jwtToken = cookie.getValue();
                    System.out.println(1);
                }
            }
        }

        if(jwtToken != null) {
            Member member = JwtUtil.getMember(jwtToken);
            System.out.println("2");
            if(member != null) {
                System.out.println("3");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(member);

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        filterChain.doFilter(request, response);
    }
}