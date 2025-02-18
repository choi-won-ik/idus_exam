package com.example.idus_exam.config.security;

import com.example.idus_exam.config.security.filter.JwtFilter;
import com.example.idus_exam.config.security.filter.LoginFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                (auth) -> auth
                        .requestMatchers("/login", "/member/signup", "/member/verify").permitAll()
                        .anyRequest().permitAll());


        // 기존에 사용자한테 설정하도록 한 쿠키(JSESSIONID)를 사용하지 않도록 하는 설정
        http.sessionManagement(AbstractHttpConfigurer::disable);

        http.addFilterAt(new LoginFilter(configuration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .deleteCookies("ATOKEN")
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
        );

        return http.build();
    }



}
