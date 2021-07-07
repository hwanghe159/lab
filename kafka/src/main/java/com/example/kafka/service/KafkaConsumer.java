package com.example.kafka.service;

import java.io.IOException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  @KafkaListener(topics = "exam", groupId = "foo")
  public void consume(String message) throws IOException {
    System.out.printf("Consumed message : %s%n", message);
  }
}