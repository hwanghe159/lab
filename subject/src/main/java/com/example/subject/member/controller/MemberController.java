package com.example.subject.member.controller;

import com.example.subject.member.dto.MemberCreateRequest;
import com.example.subject.member.dto.MemberResponse;
import com.example.subject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Long> join(@RequestBody MemberCreateRequest request) {
        MemberResponse memberResponse = memberService.join(request);
        return ResponseEntity.created(URI.create("/members/" + memberResponse.getId()))
                .body(memberResponse.getId());
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getMembers(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String email,
                                                           Pageable pageable) {
        return ResponseEntity.ok(memberService.search(name, email, pageable));
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.find(id));
    }
}
