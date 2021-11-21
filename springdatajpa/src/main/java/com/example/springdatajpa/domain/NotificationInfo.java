package com.example.springdatajpa.domain;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationInfo {

  private String message;
  private String title;
  private String imageUrl;
  private Long expireTime;
  private LinkInfo link;
  private Map<String, Object> custom;
}
