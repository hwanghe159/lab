package com.example.subject.member.service;

import com.example.subject.member.domain.Member;
import com.example.subject.member.dto.MemberCreateRequest;
import com.example.subject.member.dto.MemberResponse;
import com.example.subject.member.exception.NoSuchMemberException;
import com.example.subject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse join(MemberCreateRequest request) {
        Member savedMember = memberRepository.save(request.toEntity());
        return new MemberResponse(savedMember);
    }

    public MemberResponse find(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchMemberException(memberId));
        return new MemberResponse(member);
    }

    public List<MemberResponse> search(String name, String email, Pageable pageable) {
        Page<Member> members = memberRepository.findByNameOrEmail(name, email, pageable);
        return members.get()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
    }
}
