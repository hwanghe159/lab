package com.example.chapter9.reader;

import com.example.chapter9.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class CustomerFileReaderConfig {

  @Bean
  @StepScope
  public FlatFileItemReader<Customer> customerFileReader(
      @Value("#{jobParameters['customerFile']}") Resource inputFile
  ) {
    return new FlatFileItemReaderBuilder<Customer>()
        .name("customerItemReader")
        .resource(inputFile)
        .delimited()
        .names("firstName", "middleInitial", "lastName", "address", "city", "state", "zip")
        .targetType(Customer.class)
        .build();
  }
}
