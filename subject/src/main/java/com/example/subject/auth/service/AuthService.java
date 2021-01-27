package com.example.subject.auth.service;

import com.example.subject.auth.dto.LoginRequest;
import com.example.subject.auth.dto.LogoutRequest;
import com.example.subject.auth.dto.TokenResponse;
import com.example.subject.auth.exception.PasswordNotMatchException;
import com.example.subject.domain.Member;
import com.example.subject.exception.NoSuchMemberException;
import com.example.subject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public TokenResponse login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchMemberException(email));

        if (!member.isCorrectPassword(password)) {
            throw new PasswordNotMatchException();
        }

        return new TokenResponse(member.getEmail(),"Bearer");
    }

    public void logout(LogoutRequest request) {
        //로그아웃 처리
    }
}
