package com.example.subject.dto;

import com.example.subject.domain.Gender;
import com.example.subject.domain.Member;
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
        return new Member(name, nickname, password, phoneNumber, email, gender);
    }
}
