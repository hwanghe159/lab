package com.example.chapter9;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@RequiredArgsConstructor
public class FormattedTextFileJobConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job formatJob() {
    return this.jobBuilderFactory.get("formatJob")
        .start(formatStep())
        .incrementer(new RunIdIncrementer())
        .build();
  }

  @Bean
  public Step formatStep() {
    return this.stepBuilderFactory.get("formatStep")
        .<Customer, Customer>chunk(10)
        .reader(customerFileReader(null))
        .writer(customerItemWriter(null))
        .build();
  }

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

  @Bean
  @StepScope
  public FlatFileItemWriter<Customer> customerItemWriter(
      @Value("#{jobParameters['outputFile']}") Resource outputFile
  ) {
    return new FlatFileItemWriterBuilder<Customer>()
        .name("customerItemWriter")
        .resource(outputFile)
        .formatted()
        .format("%s %s lives at %s %s in %s, %s.")
        .names("firstName", "lastName", "address", "city", "state", "zip")
        .build();
  }
}
