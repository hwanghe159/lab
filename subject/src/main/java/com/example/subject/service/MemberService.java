package com.example.subject.service;

import com.example.subject.domain.Member;
import com.example.subject.dto.MemberCreateRequest;
import com.example.subject.dto.MemberResponse;
import com.example.subject.exception.NoSuchMemberException;
import com.example.subject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse join(MemberCreateRequest request) {
        Member savedMember = memberRepository.save(request.toEntity());
        return new MemberResponse(savedMember);
    }

    public MemberResponse find(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchMemberException(memberId));
        return new MemberResponse(member);
    }
}
