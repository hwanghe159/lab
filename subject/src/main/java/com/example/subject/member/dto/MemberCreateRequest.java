package com.example.subject.member.dto;

import com.example.subject.member.domain.Gender;
import com.example.subject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateRequest {
    private final String name;
    private final String nickname;
    private final String password;
    private final String phoneNumber;
    private final String email;
    private final Gender gender;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .password(password)
                .phoneNumber(phoneNumber)
                .email(email)
                .gender(gender)
                .build();
    }
}
