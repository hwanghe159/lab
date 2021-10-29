package com.example.chapter9.job;

import com.example.chapter9.Customer;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcNamedParameterJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private FlatFileItemReader<Customer> customerFileReader;

  @Bean
  public Job jdbcNamedParameterJob() {
    return this.jobBuilderFactory.get("jdbcNamedParameterJob")
        .start(jdbcNamedParameterStep())
        .incrementer(new RunIdIncrementer())
        .build();
  }

  @Bean
  public Step jdbcNamedParameterStep() {
    return this.stepBuilderFactory.get("jdbcNamedParameterStep")
        .<Customer, Customer>chunk(10)
        .reader(customerFileReader)
        .writer(jdbcCustomerWriter(null))
        .build();
  }

  @Bean
  public JdbcBatchItemWriter<Customer> jdbcCustomerWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Customer>()
        .dataSource(dataSource)
        .sql(
            "INSERT INTO CUSTOMER (first_name, middle_initial, last_name, address, city, state, zip) "
                + "VALUES (:firstName, :middleInitial, :lastName, :address, :city, :state, :zip)")
        .beanMapped()
        .build();
  }
}
