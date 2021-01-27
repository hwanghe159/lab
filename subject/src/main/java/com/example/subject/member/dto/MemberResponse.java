package com.example.subject.member.dto;

import com.example.subject.member.domain.Gender;
import com.example.subject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private String nickname;
    private String password;
    private String phoneNumber;
    private String email;
    private Gender gender;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
        this.phoneNumber = member.getPhoneNumber();
        this.email = member.getEmail();
        this.gender = member.getGender();
    }
}
