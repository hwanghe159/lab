package com.example.subject.member.dto;

import com.example.subject.member.domain.Gender;
import com.example.subject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String password;
    private final String phoneNumber;
    private final String email;
    private final Gender gender;

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
