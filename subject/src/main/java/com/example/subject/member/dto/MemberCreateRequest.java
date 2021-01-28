package com.example.subject.member.dto;

import com.example.subject.member.domain.Gender;
import com.example.subject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {
    private String name;
    private String nickname;
    private String password;
    private String phoneNumber;
    private String email;
    private Gender gender;

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
