package com.example.springdatajpa.component;

import com.example.springdatajpa.domain.NotificationPush;
import com.example.springdatajpa.service.NotificationPushService;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EqualsAndHashCode
public class AppPushComponent {

  private final NotificationPushService notificationPushService;

  public NotificationPush save(NotificationPush push) {
    NotificationPush save = notificationPushService.save(push);
    return save;
  }
}
