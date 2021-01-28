package com.example.subject.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String nickname;

    private String password;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 100)
    private String email;

    private Gender gender;

    @Builder
    public Member(String name, String nickname, String password, String phoneNumber, String email, Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}
