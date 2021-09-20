package com.example.jdbcbatch.cursor;

import com.example.jdbcbatch.Customer;
import com.example.jdbcbatch.CustomerRowMapper;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;

@Configuration
public class JdbcCursorJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job jdbcCursorJob() {
    return this.jobBuilderFactory.get("jdbcCursorJob")
        .start(jdbcCursorStep())
        .build();
  }

  @Bean
  public Step jdbcCursorStep() {
    return this.stepBuilderFactory.get("jdbcCursorStep")
        .<Customer, Customer>chunk(10)
        .reader(customJdbcCursorItemReader(null))
        .writer(jdbcCursorItemWriter())
        .build();
  }

  @Bean
  public JdbcCursorItemReader<Customer> customJdbcCursorItemReader(DataSource dataSource) {
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
  public ArgumentPreparedStatementSetter citySetter(
      @Value("#{jobParameters['city']}") String city) {

    return new ArgumentPreparedStatementSetter(new Object[]{city});
  }

  @Bean
  public ItemWriter<Customer> jdbcCursorItemWriter() {
    return items -> items.forEach(System.out::println);
  }
}
