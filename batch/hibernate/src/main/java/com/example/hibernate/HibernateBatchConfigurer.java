package com.example.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class HibernateBatchConfigurer extends DefaultBatchConfigurer {

  private DataSource dataSource;
  private SessionFactory sessionFactory;
  private PlatformTransactionManager transactionManager;

  public HibernateBatchConfigurer(
      DataSource dataSource, EntityManagerFactory entityManagerFactory
  ) {
    super(dataSource);
    this.dataSource = dataSource;
    this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    this.transactionManager = new HibernateTransactionManager(this.sessionFactory);
  }

  // 일반적인 DataSource커넥션과 하이버네이트 세션을 아우르는 트랜잭션 매니저
  @Override
  public PlatformTransactionManager getTransactionManager() {
    return this.transactionManager;
  }
}
