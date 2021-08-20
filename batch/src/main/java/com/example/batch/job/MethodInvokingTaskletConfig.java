package com.example.batch.job;

import java.util.concurrent.Callable;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodInvokingTaskletConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job methodInvokingJob() {
    return jobBuilderFactory.get("callableJob")
        .start(methodInvokingStep())
        .build();
  }

  @Bean
  public Step methodInvokingStep() {
    return stepBuilderFactory.get("methodInvokingStep")
        .tasklet(methodInvokingTasklet())
        .build();
  }

  @Bean
  public MethodInvokingTaskletAdapter methodInvokingTasklet() {
    MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
    adapter.setTargetObject(service());
    adapter.setTargetMethod("method");
    return adapter;
  }

  @Bean
  public Object service() {
    return null;
  }
}
