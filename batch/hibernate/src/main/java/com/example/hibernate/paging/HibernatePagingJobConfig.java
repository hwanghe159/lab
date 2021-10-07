package com.example.hibernate.paging;

import com.example.hibernate.Customer;
import java.util.Collections;
import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernatePagingItemReader;
import org.springframework.batch.item.database.builder.HibernatePagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernatePagingJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job hibernatePagingJob() {
    return this.jobBuilderFactory.get("hibernatePagingJob")
        .start(hibernatePagingStep())
        .build();
  }

  @Bean
  public Step hibernatePagingStep() {
    return this.stepBuilderFactory.get("hibernatePagingStep")
        .<Customer, Customer>chunk(10)
        .reader(hibernatePagingItemReader(null, null))
        .writer(hibernatePagingItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public HibernatePagingItemReader<Customer> hibernatePagingItemReader(
      EntityManagerFactory entityManagerFactory, @Value("#{jobParameters['city']}") String city
  ) {
    return new HibernatePagingItemReaderBuilder<Customer>()
        .name("hibernatePagingItemReader")
        .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
        .queryString("from Customer where city = :city order by lastName")
        .parameterValues(Collections.singletonMap("city", city))
        .pageSize(10)
        .build();
  }

  @Bean
  public ItemWriter<Customer> hibernatePagingItemWriter() {
    return items -> items.forEach(System.out::println);
  }
}
