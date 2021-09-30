package com.example.springdatamongodb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringdatamongodbApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringdatamongodbApplication.class, args);
  }

}
