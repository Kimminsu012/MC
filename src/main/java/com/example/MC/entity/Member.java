package com.example.MC.entity;

import com.example.MC.constant.Role;
import com.example.MC.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column(name="user_nick")
    private String nick;

    @Column
    @ColumnDefault("아무개")
    private String name;

    @Column
    private Long tell;

    @Column
    private String zipCode; //우편번호

    @Column
    private String addr1; //기본주소

    @Column
    private  String addr2; // 상세주소

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);
        member.setNick(memberDto.getNick());
        member.setName(memberDto.getName());
        member.setTell(memberDto.getTell());
        member.setAddr1(member.getAddr1());
        member.setAddr2(member.getAddr2());
        member.setRole(Role.USER);
        return member;
    }
}
