package com.example.webflux.service;

import com.example.webflux.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductDomain {

  public Mono<ProductResponse> getProduct() {
    log.info("작품 요청함");
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080/api/v1/products/1")
        .build();
    return webClient.get()
        .retrieve()
        .bodyToMono(ProductResponse.class);
  }
}
