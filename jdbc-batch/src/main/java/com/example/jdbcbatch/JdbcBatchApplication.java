package com.example.jdbcbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class JdbcBatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(JdbcBatchApplication.class, args);
  }

}
