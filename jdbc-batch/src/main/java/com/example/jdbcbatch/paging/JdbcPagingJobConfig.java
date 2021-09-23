package com.example.jdbcbatch.paging;

import com.example.jdbcbatch.Customer;
import com.example.jdbcbatch.CustomerRowMapper;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job jdbcPagingJob() {
    return this.jobBuilderFactory.get("jdbcPagingJob")
        .start(jdbcPagingStep())
        .build();
  }

  @Bean
  public Step jdbcPagingStep() {
    return this.stepBuilderFactory.get("jdbcPagingStep")
        .<Customer, Customer>chunk(10)
        .reader(customJdbcPagingItemReader(null, null, null))
        .writer(jdbcPagingItemWriter())
        .build();
  }

  @StepScope
  @Bean
  public JdbcPagingItemReader<Customer> customJdbcPagingItemReader(
      DataSource dataSource,
      PagingQueryProvider queryProvider,
      @Value("#{jobParameters['city']}") String city
  ) {

    Map<String, Object> parameterValues = new HashMap<>(1);
    parameterValues.put("city", city);
    return new JdbcPagingItemReaderBuilder<Customer>()
        .name("customerItemReader")
        .dataSource(dataSource)
        .queryProvider(queryProvider)
        .parameterValues(parameterValues)
        .pageSize(10)
        .rowMapper(new CustomerRowMapper())
        .build();
  }

  // 제공되는 DataSource로 작업 중인 데이터베이스 타입을 결정한다
  @Bean
  public SqlPagingQueryProviderFactoryBean pagingQueryProvider(DataSource dataSource) {
    SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();
    factoryBean.setSelectClause("select *");
    factoryBean.setFromClause("from customer");
    factoryBean.setWhereClause("where city = :city");
    factoryBean.setSortKey("lastName");
    factoryBean.setDataSource(dataSource);
    return factoryBean;
  }

  @Bean
  public ItemWriter<Customer> jdbcPagingItemWriter() {
    return items -> items.forEach(System.out::println);
  }
}