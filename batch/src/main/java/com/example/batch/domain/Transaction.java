package com.example.batch.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date timestamp;

  private double amount;

  private Long accountId;

  public Transaction(Date timestamp, double amount, Long accountId) {
    this.timestamp = timestamp;
    this.amount = amount;
    this.accountId = accountId;
  }
}
