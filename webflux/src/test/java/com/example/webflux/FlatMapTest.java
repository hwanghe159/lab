package com.example.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FlatMapTest {

  @Test
  void flatMap() throws InterruptedException {
    Flux.range(2, 8)
        .flatMap(dan ->
            Flux.range(1, 9)
                .publishOn(Schedulers.parallel())
                .map(n -> dan + " * " + n + " = " + dan * n)
        )
        .subscribe(log::info);

    Thread.sleep(100L);
  }
}
