package com.example.storedprocedure;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class StoredprocedureApplication {

  public static void main(String[] args) {
    SpringApplication.run(StoredprocedureApplication.class, args);
  }

}
