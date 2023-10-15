package com.example.webflux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class MergeTest {

  public static Map<String, Mono<String>> nppMap = new HashMap<>();

  static {
    nppMap.put("Ontario",
        Mono.just("Ontario Done").delayElement(Duration.ofMillis(1500L)));
    nppMap.put("Vermont",
        Mono.just("Vermont Done").delayElement(Duration.ofMillis(400L)));
    nppMap.put("New Hampshire",
        Mono.just("New Hampshire Done").delayElement(Duration.ofMillis(700L)));
    nppMap.put("New Jersey",
        Mono.just("New Jersey Done").delayElement(Duration.ofMillis(500L)));
    nppMap.put("Ohio",
        Mono.just("Ohio Done").delayElement(Duration.ofMillis(1000L)));
    nppMap.put("Michigan",
        Mono.just("Michigan Done").delayElement(Duration.ofMillis(200L)));
    nppMap.put("Illinois",
        Mono.just("Illinois Done").delayElement(Duration.ofMillis(300L)));
    nppMap.put("Virginia",
        Mono.just("Virginia Done").delayElement(Duration.ofMillis(600L)));
    nppMap.put("North Carolina",
        Mono.just("North Carolina Done").delayElement(Duration.ofMillis(800L)));
    nppMap.put("Georgia",
        Mono.just("Georgia Done").delayElement(Duration.ofMillis(900L)));
  }

  private static List<Mono<String>> getMeltDownRecoveryMessage(String[] usaStates) {
    List<Mono<String>> messages = new ArrayList<>();
    for (String state : usaStates) {
      messages.add(nppMap.get(state));
    }

    return messages;
  }

  @Test
  void merge() throws InterruptedException {
    String[] usaStates = {
        "Ohio", "Michigan", "New Jersey", "Illinois", "New Hampshire",
        "Virginia", "Vermont", "North Carolina", "Ontario", "Georgia"
    };

    Flux
        .merge(getMeltDownRecoveryMessage(usaStates))
        .subscribe(log::info);

    Thread.sleep(2000L);
  }
}
