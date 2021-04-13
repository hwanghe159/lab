package com.example.webclient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebclientApplicationTests {

  @Test
  void contextLoads() throws InterruptedException, ExecutionException {
    Future<String> completableFuture = calculateAsync();

    System.out.println(
        Thread.currentThread() + "completableFuture.get() 직전, 시간:" + LocalDateTime.now()
            .toString());
    String result = completableFuture.get();
    System.out.println(
        Thread.currentThread() + "completableFuture.get() 직후, 시간:" + LocalDateTime.now()
            .toString());

    assertEquals("Hello", result);
  }

  public Future<String> calculateAsync() {
    System.out.println(Thread.currentThread() + "calculateAsync 입장");
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    Executors.newCachedThreadPool().submit(() -> {

      System.out.println(Thread.currentThread() + "Thread.sleep(1000) 직전");
      Thread.sleep(1000);
      System.out.println(Thread.currentThread() + "Thread.sleep(1000) 직후");

      System.out.println(Thread.currentThread() + "completableFuture.complete(\"Hello\") 직전");
      completableFuture.complete("Hello");
      System.out.println(Thread.currentThread() + "completableFuture.complete(\"Hello\") 직후");
      return null;
    });

    System.out.println(Thread.currentThread() + "calculateAsync 끝");
    return completableFuture;
  }
}
