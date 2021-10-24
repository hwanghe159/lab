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
public class FormattedTextFileJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private FlatFileItemReader<Customer> customerFileReader;
  private final Resource outputResource
      = new FileSystemResource("chapter9/output/formattedCustomers.txt");

  @Bean
  public Job formattedTextFileJob() {
    return this.jobBuilderFactory.get("formattedTextFileJob")
        .start(formattedTextFileStep())
        .incrementer(new RunIdIncrementer())
        .build();
  }

  @Bean
  public Step formattedTextFileStep() {
    return this.stepBuilderFactory.get("formattedTextFileStep")
        .<Customer, Customer>chunk(10)
        .reader(customerFileReader)
        .writer(formattedItemWriter())
        .build();
  }

  @Bean
  public FlatFileItemWriter<Customer> formattedItemWriter(
  ) {
    return new FlatFileItemWriterBuilder<Customer>()
        .name("customerItemWriter")
        .resource(outputResource)
        .formatted()
        .format("%s %s lives at %s %s in %s, %s.")
        .names("firstName", "lastName", "address", "city", "state", "zip")
        .build();
  }
}
