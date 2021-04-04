package com.example.webclient;

import java.util.concurrent.Future;

public interface CoffeeUseCase {

  int getPrice(String name);

  Future<Integer> getPriceAsync(String name);
}
