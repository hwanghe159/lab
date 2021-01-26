package com.example.subject.controller;

import com.example.subject.dto.MemberCreateRequest;
import com.example.subject.dto.MemberResponse;
import com.example.subject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> find(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.find(id));
    }
}
