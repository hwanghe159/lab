package com.example.webclient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoffeeComponent implements CoffeeUseCase {

  private final CoffeeRepository coffeeRepository;
  private final Executor executor = Executors.newFixedThreadPool(10);

  @Override
  public int getPrice(String name) {
    return coffeeRepository.getPriceByName(name);
  }

  @Override
  public CompletableFuture<Integer> getPriceAsync(String name) {
    return CompletableFuture.supplyAsync(
        () -> coffeeRepository.getPriceByName(name),
        executor
    );
  }
}
