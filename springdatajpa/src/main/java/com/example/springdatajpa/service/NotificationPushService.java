package com.example.springdatajpa.service;

import com.example.springdatajpa.domain.NotificationPush;
import com.example.springdatajpa.domain.NotificationPushRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationPushService {

  private final NotificationPushRepository notificationPushRepository;

  public NotificationPush save(NotificationPush notificationPush) {
    NotificationPush savedPush = notificationPushRepository.save(notificationPush);
    return savedPush;
  }
}
