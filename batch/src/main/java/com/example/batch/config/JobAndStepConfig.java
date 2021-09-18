package com.example.batch.config;

import java.util.Collections;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;

@Configuration
public class JobAndStepConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job job() {
    return this.jobBuilderFactory.get("job")
        .start(step1())
        .on("STOPPED").stopAndRestart(step1())
        .from(step1())
        .on("*").to(step2())
        .from(step2())
        .next(step3()).end()
        .build();
  }

  private Step step1() {
    return this.stepBuilderFactory.get("step1")
        .chunk(100)
        .reader(transactionReader())
        .writer(transactionWriter(null))
        .allowStartIfComplete(true)
        .listener(stransactionReader())
        .build();
  }

  private Step step2() {
    return this.stepBuilderFactory.get("step2")
        .<Customer, Customer>chunk(10)
        .reader(null)
        .writer(null)
        .faultTolerant()
        .skipPolicy()
  }

  private Step step3() {
    return null;
  }

  @Bean
  public JdbcCursorItemReader<Customer> customerItemReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Customer>()
        .name("customerItemReader")
        .dataSource(dataSource)
        .sql("select * from customer where city = ?")
        .rowMapper(new CustomerRowMapper())
        .preparedStatementSetter(citySetter(null))
        .build();
  }

  @Bean
  @StepScope
  public PreparedStatementSetter citySetter(@Value("#{jobParameters['city']}") String city) {
    return new ArgumentPreparedStatementSetter(new Object[]{city});
  }

  @Bean
  public StepExecutionListener promotionListener() {
    ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
    listener.setKeys(new String[]{"name"});
    return listener;
  }

  @Bean
  @StepScope
  public HibernateCursorItemReader<Customer> customerItemReader(
      EntityManagerFactory entityManagerFactory, @Value("#{jobParameters['city']}") String city
  ) {
    return new HibernateCursorItemReaderBuilder<Customer>()
        .name("customerItemReader")
        .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
        .queryString("from Customer where city = :city")
        .parameterValues(Collections.singletonMap("city", city))
        .build();
  }

}
