package com.example.webflux.controller;

import com.example.webflux.dto.ProductResponse;
import com.example.webflux.dto.UserResponse;
import com.example.webflux.service.ProductDomain;
import com.example.webflux.service.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequiredArgsConstructor
public class AggregateController {

  private final UserDomain userDomain;
  private final ProductDomain productDomain;

  @GetMapping("/aggregate")
  public Mono<String> aggregate() {
    return Mono.zip(userDomain.getUser(), productDomain.getProduct())
        .map(this::combine);
  }

  private String combine(Tuple2<UserResponse, ProductResponse> tuple) {
    return String.format("'%s' 회원님의 제품 '%s'!", tuple.getT1().getName(), tuple.getT2().getName());
  }
}
