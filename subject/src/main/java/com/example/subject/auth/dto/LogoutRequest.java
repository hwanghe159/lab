package com.example.subject.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogoutRequest {
    private final String accessToken;
    private final String tokenType;
}
