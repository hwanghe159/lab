//package com.example.chapter9.job;
//
//import com.example.chapter9.CustomerEntity;
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.BatchConfigurationException;
//import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.batch.item.database.HibernateItemWriter;
//import org.springframework.batch.item.database.builder.HibernateItemWriterBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class HibernateJobConfig {
//
//  @Autowired
//  private JobBuilderFactory jobBuilderFactory;
//  @Autowired
//  private StepBuilderFactory stepBuilderFactory;
//  @Autowired
//  private FlatFileItemReader<CustomerEntity> customerEntityFileReader;
//
//  @Bean
//  public Job hibernateFormatJob() {
//    return this.jobBuilderFactory.get("hibernateFormatJob")
//        .start(hibernateFormatStep())
//        .build();
//  }
//
//  @Bean
//  public Step hibernateFormatStep() {
//    return this.stepBuilderFactory.get("hibernateFormatStep")
//        .<CustomerEntity, CustomerEntity>chunk(10)
//        .reader(customerEntityFileReader)
//        .writer(hibernateItemWriter(null))
//        .build();
//  }
//
//  @Bean
//  public HibernateItemWriter<CustomerEntity> hibernateItemWriter(
//      EntityManagerFactory entityManager
//  ) {
//    return new HibernateItemWriterBuilder<CustomerEntity>()
//        .sessionFactory(entityManager.unwrap(SessionFactory.class)) //필수
//        .build();
//  }
//
//  @Configuration
//  private static class HibernateBatchConfigurer implements BatchConfigurer {
//
//    private DataSource dataSource;
//    private SessionFactory sessionFactory;
//    private JobRepository jobRepository;
//    private PlatformTransactionManager transactionManager;
//    private JobLauncher jobLauncher;
//    private JobExplorer jobExplorer;
//
//    public HibernateBatchConfigurer(
//        DataSource dataSource, EntityManagerFactory entityManagerFactory
//    ) {
//      this.dataSource = dataSource;
//      this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//    }
//
//    @Override
//    public JobRepository getJobRepository() throws Exception {
//      return this.jobRepository;
//    }
//
//    @Override
//    public PlatformTransactionManager getTransactionManager() throws Exception {
//      return this.transactionManager;
//    }
//
//    @Override
//    public JobLauncher getJobLauncher() throws Exception {
//      return this.jobLauncher;
//    }
//
//    @Override
//    public JobExplorer getJobExplorer() throws Exception {
//      return this.jobExplorer;
//    }
//
//    @PostConstruct
//    public void initialize() {
//
//      try {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
//            sessionFactory);
//        transactionManager.afterPropertiesSet();
//
//        this.transactionManager = transactionManager;
//
//        this.jobRepository = createJobRepository();
//        this.jobExplorer = createJobExplorer();
//        this.jobLauncher = createJobLauncher();
//
//      } catch (Exception e) {
//        throw new BatchConfigurationException(e);
//      }
//    }
//
//    private JobLauncher createJobLauncher() throws Exception {
//      SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//
//      jobLauncher.setJobRepository(this.jobRepository);
//      jobLauncher.afterPropertiesSet();
//
//      return jobLauncher;
//    }
//
//    private JobExplorer createJobExplorer() throws Exception {
//      JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
//
//      jobExplorerFactoryBean.setDataSource(this.dataSource);
//      jobExplorerFactoryBean.afterPropertiesSet();
//
//      return jobExplorerFactoryBean.getObject();
//    }
//
//    private JobRepository createJobRepository() throws Exception {
//      JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//
//      jobRepositoryFactoryBean.setDataSource(this.dataSource);
//      jobRepositoryFactoryBean.setTransactionManager(this.transactionManager);
//      jobRepositoryFactoryBean.afterPropertiesSet();
//
//      return jobRepositoryFactoryBean.getObject();
//    }
//  }
//}
