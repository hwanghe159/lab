package com.example.kafka.controller;

import com.example.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {

  private final KafkaProducer kafkaProducer;

  @PostMapping
  public String sendMessage(@RequestParam("message") String message) {
    kafkaProducer.sendMessage(message);
    return "success";
  }
}