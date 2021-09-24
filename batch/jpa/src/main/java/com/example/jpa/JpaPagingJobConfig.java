package com.example.jpa;

import java.util.Collections;
import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaPagingJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job jpaPagingJob() {
    return this.jobBuilderFactory.get("jpaPagingJob")
        .start(jpaPagingStep())
        .build();
  }

  @Bean
  public Step jpaPagingStep() {
    return this.stepBuilderFactory.get("jpaPagingStep")
        .<Customer, Customer>chunk(10)
        .reader(jpaPagingItemReader(null, null))
        .writer(jpaPagingItemWriter())
        .build();
  }

  @StepScope
  @Bean
  public JpaPagingItemReader<Customer> jpaPagingItemReader(
      EntityManagerFactory entityManagerFactory, @Value("#{jobParameters['city']}") String city
  ) {
    CustomerByCityQueryProvider queryProvider = new CustomerByCityQueryProvider();
    queryProvider.setCityName(city);

    return new JpaPagingItemReaderBuilder<Customer>()
        .name("jpaPagingItemReader")
        .entityManagerFactory(entityManagerFactory)
        .queryProvider(queryProvider)
        .build();
  }

  @Bean
  public ItemWriter<Customer> jpaPagingItemWriter() {
    return items -> items.forEach(System.out::println);
  }
}