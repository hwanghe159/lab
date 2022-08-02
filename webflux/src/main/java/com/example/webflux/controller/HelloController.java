package com.example.webflux.controller;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class HelloController {

  @GetMapping("/")
  public Flux<String> hello() {
    return Flux.just("Hello", "World");
  }

  @GetMapping("/stream")
  public Flux<Map<String, Integer>> stream() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
    return Flux.fromStream(stream.limit(10))
        .map(i -> Collections.singletonMap("value", i));
  }

  @GetMapping("/stream/no-limit")
  public Flux<Map<String, Integer>> noLimitStream() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
    return Flux.fromStream(stream)
        .map(i -> Collections.singletonMap("value", i));
  }
}