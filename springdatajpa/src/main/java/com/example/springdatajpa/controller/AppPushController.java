package com.example.springdatajpa.controller;

import com.example.springdatajpa.component.AppPushComponent;
import com.example.springdatajpa.domain.NotificationPush;
import com.example.springdatajpa.dto.AppPushRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AppPushController {

  private final AppPushComponent appPushComponent;
  private final ObjectMapper objectMapper;

  @PostMapping("/notifications")
  public ResponseEntity<Void> create(@RequestBody AppPushRequest request) throws JsonProcessingException {
    logRequest(request);

    for (int i = 0; i < 1000; i++) {
    }
    NotificationPush push = new NotificationPush(request.getNotification(), request.getReceiver());
    appPushComponent.save(push);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void logRequest(Object request) throws JsonProcessingException {
    log.info(objectMapper.writeValueAsString(request));
  }
}
