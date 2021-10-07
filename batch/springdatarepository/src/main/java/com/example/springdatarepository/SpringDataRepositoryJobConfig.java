package com.example.springdatarepository;

import java.util.Collections;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

@Configuration
public class SpringDataRepositoryJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job springDataRepositoryJob() {
    return this.jobBuilderFactory.get("springDataRepositoryJob")
        .start(springDataRepositoryStep())
        .build();
  }

  @Bean
  public Step springDataRepositoryStep() {
    return this.stepBuilderFactory.get("springDataRepositoryStep")
        .<Customer, Customer>chunk(10)
        .reader(customerItemReader(null, null))
        .writer(itemWriter())
        .build();
  }

  @Bean
  @StepScope
  public RepositoryItemReader<Customer> customerItemReader(
      CustomerRepository repository, @Value("#{jobParameters['city']}") String city
  ) {
    return new RepositoryItemReaderBuilder<Customer>()
        .name("customerItemReader")
        .arguments(Collections.singletonList(city))
        .methodName("findByCity")
        .repository(repository)
        .sorts(Collections.singletonMap("lastName", Sort.Direction.ASC))
        .build();
  }

  @Bean
  public ItemWriter<Customer> itemWriter() {
    return items -> items.forEach(System.out::println);
  }
}
