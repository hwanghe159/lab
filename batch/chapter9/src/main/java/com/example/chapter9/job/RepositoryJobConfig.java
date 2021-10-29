package com.example.chapter9.job;

import com.example.chapter9.CustomerEntity;
import com.example.chapter9.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = CustomerEntity.class)
public class RepositoryJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private FlatFileItemReader<CustomerEntity> customerEntityFileReader;

  @Bean
  public Job repositoryFormatJob() {
    return this.jobBuilderFactory.get("repositoryFormatJob")
        .start(repositoryFormatStep())
        .build();
  }

  @Bean
  public Step repositoryFormatStep() {
    return this.stepBuilderFactory.get("repositoryFormatStep")
        .<CustomerEntity, CustomerEntity>chunk(10)
        .reader(customerEntityFileReader)
        .writer(repositoryItemWriter(null))
        .build();
  }

  @Bean
  public RepositoryItemWriter<CustomerEntity> repositoryItemWriter(CustomerRepository repository) {
    return new RepositoryItemWriterBuilder<CustomerEntity>()
        .repository(repository)
        .methodName("save")
        .build();
  }
}
