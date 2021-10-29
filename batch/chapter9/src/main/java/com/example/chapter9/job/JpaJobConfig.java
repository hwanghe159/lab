package com.example.chapter9.job;

import com.example.chapter9.CustomerEntity;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JpaJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private FlatFileItemReader<CustomerEntity> customerEntityFileReader;

  @Bean
  public Job jpaFormatJob() {
    return this.jobBuilderFactory.get("jpaFormatJob")
        .start(jpaFormatStep())
        .build();
  }

  @Bean
  public Step jpaFormatStep() {
    return this.stepBuilderFactory.get("jpaFormatStep")
        .<CustomerEntity, CustomerEntity>chunk(10)
        .reader(customerEntityFileReader)
        .writer(jpaItemWriter(null))
        .build();
  }

  @Bean
  public JpaItemWriter<CustomerEntity> jpaItemWriter(
      EntityManagerFactory entityManagerFactory
  ) {
    JpaItemWriter<CustomerEntity> jpaItemWriter = new JpaItemWriter<>();
    jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
    return jpaItemWriter;
  }

  @Configuration
  private static class JpaBatchConfigurer implements BatchConfigurer {

    private DataSource dataSource;
    private EntityManagerFactory entityManagerFactory;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private JobLauncher jobLauncher;
    private JobExplorer jobExplorer;

    public JpaBatchConfigurer(
        DataSource dataSource, EntityManagerFactory entityManagerFactory
    ) {
      this.dataSource = dataSource;
      this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public JobRepository getJobRepository() throws Exception {
      return this.jobRepository;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
      return this.transactionManager;
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
      return this.jobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
      return this.jobExplorer;
    }

    @PostConstruct
    public void initialize() {

      try {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.afterPropertiesSet();

        this.transactionManager = transactionManager;

        this.jobRepository = createJobRepository();
        this.jobExplorer = createJobExplorer();
        this.jobLauncher = createJobLauncher();

      } catch (Exception e) {
        throw new BatchConfigurationException(e);
      }
    }

    private JobLauncher createJobLauncher() throws Exception {
      SimpleJobLauncher jobLauncher = new SimpleJobLauncher();

      jobLauncher.setJobRepository(this.jobRepository);
      jobLauncher.afterPropertiesSet();

      return jobLauncher;
    }

    private JobExplorer createJobExplorer() throws Exception {
      JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();

      jobExplorerFactoryBean.setDataSource(this.dataSource);
      jobExplorerFactoryBean.afterPropertiesSet();

      return jobExplorerFactoryBean.getObject();
    }

    private JobRepository createJobRepository() throws Exception {
      JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();

      jobRepositoryFactoryBean.setDataSource(this.dataSource);
      jobRepositoryFactoryBean.setTransactionManager(this.transactionManager);
      jobRepositoryFactoryBean.afterPropertiesSet();

      return jobRepositoryFactoryBean.getObject();
    }
  }
}
