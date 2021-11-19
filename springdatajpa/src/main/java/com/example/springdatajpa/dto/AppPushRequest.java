package com.example.springdatajpa.dto;

import com.example.springdatajpa.domain.NotificationInfo;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppPushRequest {

  private NotificationInfo notification;
  private Set<Long> receiver;
}