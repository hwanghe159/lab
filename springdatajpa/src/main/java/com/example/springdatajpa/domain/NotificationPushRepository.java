package com.example.springdatajpa.domain;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationPushRepository extends JpaRepository<NotificationPush, BigInteger> {

}
