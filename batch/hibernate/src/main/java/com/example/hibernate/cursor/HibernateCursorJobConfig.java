package com.example.hibernate.cursor;

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
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateCursorJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job hibernateCursorJob() {
    return this.jobBuilderFactory.get("hibernateCursorJob")
        .start(hibernateCursorStep())
        .build();
  }

  @Bean
  public Step hibernateCursorStep() {
    return this.stepBuilderFactory.get("hibernateCursorStep")
        .<Customer, Customer>chunk(10)
        .reader(hibernateCursorItemReader(null, null))
        .writer(hibernateCursorItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public HibernateCursorItemReader<Customer> hibernateCursorItemReader(
      EntityManagerFactory entityManagerFactory, @Value("#{jobParameters['city']}") String city
  ) {
    return new HibernateCursorItemReaderBuilder<Customer>()
        .name("hibernateCursorItemReader")
        .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
        .queryString("from Customer where city = :city")
        .parameterValues(Collections.singletonMap("city", city))
        .build();
  }

  @Bean
  public ItemWriter<Customer> hibernateCursorItemWriter() {
    return items -> items.forEach(System.out::println);
  }
}
