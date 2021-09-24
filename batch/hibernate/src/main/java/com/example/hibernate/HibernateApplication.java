package com.example.hibernate;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class HibernateApplication {

  public static void main(String[] args) {
    SpringApplication.run(HibernateApplication.class, args);
  }

}
