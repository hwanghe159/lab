package com.example.subject.member.dto;

import com.example.subject.member.domain.Gender;
import com.example.subject.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MemberCreateRequest {
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "[가-힣a-zA-Z]+")
    private final String name;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[a-z]+")
    private final String nickname;

    @NotNull
    @Length(min = 10)
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{0,10}")
    private final String password;

    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "[0-9]+")
    private final String phoneNumber;

    @NotNull
    @Length(max = 100)
    @Email
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
