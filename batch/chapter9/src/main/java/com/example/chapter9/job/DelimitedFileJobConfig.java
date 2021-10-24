package com.example.chapter9.job;

import com.example.chapter9.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class DelimitedFileJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private FlatFileItemReader<Customer> customerFileReader;
  private final Resource outputResource
      = new FileSystemResource("chapter9/output/delimitedCustomers.txt");

  @Bean
  public Job delimitedFileJob() {
    return this.jobBuilderFactory.get("delimitedFileJob")
        .start(delimitedFileStep())
        .incrementer(new RunIdIncrementer())
        .build();
  }

  @Bean
  public Step delimitedFileStep() {
    return this.stepBuilderFactory.get("delimitedFileStep")
        .<Customer, Customer>chunk(10)
        .reader(customerFileReader)
        .writer(delimitedItemWriter())
        .build();
  }

  @Bean
  public FlatFileItemWriter<Customer> delimitedItemWriter(
  ) {
    return new FlatFileItemWriterBuilder<Customer>()
        .name("customerItemWriter")
        .resource(outputResource)
        .delimited()
        .delimiter(";")
        .names("zip", "state", "city", "address", "lastName", "firstName")
        .build();
  }
}
