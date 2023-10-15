package com.example.webflux;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class ZipTest {

  @Test
  void zip() throws InterruptedException {
    Flux
        .zip(
            Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
            Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L)),
            (n1, n2) -> n1 * n2
        )
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(2500L);
  }
}
