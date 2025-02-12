package com.example.idus_exam.member.model;

import com.example.idus_exam.emailverify.model.EmailVerify;
import com.example.idus_exam.order.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false,length = 20)
    private String name;
    @Column(nullable = false,length = 30)
    private String nickName;
    @Column(nullable = false,length = 200)
    private String password;
    @Column(nullable = false,unique = true,length = 100)
    private String email;
    @Column(nullable = false)
    private Long phone;

    private boolean enabled;

    @OneToMany(mappedBy = "member")
    private List<EmailVerify> emailVerifyList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();

    @Enumerated(EnumType.STRING)  // Enum 값을 문자열로 저장 (권장)
    private Gender gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    public void verify() {
        this.enabled = true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
