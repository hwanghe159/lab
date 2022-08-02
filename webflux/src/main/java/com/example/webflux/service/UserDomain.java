package com.example.webflux.service;

import com.example.webflux.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UserDomain {

  public Mono<UserResponse> getUser() {
    log.info("유저 요청함");
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080/api/v1/users/1")
        .build();
    return webClient.get()
        .retrieve()
        .bodyToMono(UserResponse.class);
  }
}
