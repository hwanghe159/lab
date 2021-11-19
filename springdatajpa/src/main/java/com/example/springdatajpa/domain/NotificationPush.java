package com.example.springdatajpa.domain;

import com.example.springdatajpa.component.NotificationInfoConverter;
import com.example.springdatajpa.component.ReceiverConverter;
import java.math.BigInteger;
import java.util.Set;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationPush {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Convert(converter = NotificationInfoConverter.class)
  private NotificationInfo notification;

  @Convert(converter = ReceiverConverter.class)
  private Set<Long> receiver;

  public NotificationPush(NotificationInfo notification, Set<Long> receiver) {
    this.notification = notification;
    this.receiver = receiver;
  }
}
