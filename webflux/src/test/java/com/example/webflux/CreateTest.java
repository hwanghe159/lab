package com.example.webflux;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
public class CreateTest {

  final static List<Integer> DATA_SOURCE = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  static int SIZE = 0;
  static int COUNT = -1;

  @Test
  public void createTest() {
    log.info("# start");
    Flux.create((FluxSink<Integer> sink) -> {
      sink.onRequest(n -> {
        try {
          Thread.sleep(1000L);
          for (int i = 0; i < n; i++) {
            if (COUNT >= 9) {
              sink.complete();
            } else {
              COUNT++;
              sink.next(DATA_SOURCE.get(COUNT));
            }
          }
        } catch (InterruptedException e) {
        }
      });

      sink.onDispose(() -> log.info("# clean up"));
    }).subscribe(new BaseSubscriber<>() {
      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        request(2);
      }

      @Override
      protected void hookOnNext(Integer value) {
        SIZE++;
        log.info("# onNext: {}", value);
        if (SIZE == 2) {
          request(2);
          SIZE = 0;
        }
      }

      @Override
      protected void hookOnComplete() {
        log.info("# onComplete");
      }
    });
  }
}
