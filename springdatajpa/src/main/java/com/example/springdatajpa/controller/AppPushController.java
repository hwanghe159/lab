package com.example.springdatajpa.controller;

import com.example.springdatajpa.domain.NotificationPush;
import com.example.springdatajpa.dto.AppPushRequest;
import com.example.springdatajpa.service.NotificationPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppPushController {

  private final NotificationPushService notificationPushService;

  @PostMapping("/notifications")
  public ResponseEntity<Void> create(@RequestBody AppPushRequest request) {
    NotificationPush push = new NotificationPush(request.getNotification(), request.getReceiver());
    notificationPushService.save(push);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
