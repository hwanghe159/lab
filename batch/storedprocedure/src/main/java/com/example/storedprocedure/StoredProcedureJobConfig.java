package com.example.storedprocedure;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.StoredProcedureItemReader;
import org.springframework.batch.item.database.builder.StoredProcedureItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.SqlParameter;

@Configuration
public class StoredProcedureJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job storedProcedureJob() {
    return this.jobBuilderFactory.get("storedProcedureJob")
        .start(storedProcedureStep())
        .build();
  }

  @Bean
  public Step storedProcedureStep() {
    return this.stepBuilderFactory.get("storedProcedureStep")
        .<Customer, Customer>chunk(10)
        .reader(customerItemReader(null, null))
        .writer(itemWriter())
        .build();
  }

  @Bean
  @StepScope
  public StoredProcedureItemReader<Customer> customerItemReader(
      DataSource dataSource, @Value("#{jobParameters['city']}") String city
  ) {
    return new StoredProcedureItemReaderBuilder<Customer>()
        .name("customerItemReader")
        .dataSource(dataSource)
        .procedureName("customer_list")
        .parameters(new SqlParameter[]{new SqlParameter("cityOption", Types.VARCHAR)})
        .preparedStatementSetter(new ArgumentPreparedStatementSetter(new Object[]{city}))
        .rowMapper(new CustomerRowMapper())
        .build();
  }

  @Bean
  public ItemWriter<Customer> itemWriter() {
    return items -> items.forEach(System.out::println);
  }
}
