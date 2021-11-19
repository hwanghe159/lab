package com.example.springdatajpa.domain;

import java.util.Map;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class NotificationInfo {

  private String message;
  private String title;
  private String imageUrl;
  private String trackingTag;
  private Long expireTime;
  private Boolean isScreenPush;
  private LinkInfo link;
  private Map<String, Object> custom;
}
