package com.example.batch.job;

import java.util.concurrent.Callable;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.CallableTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallableTaskletConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job callableJob(){
    return jobBuilderFactory.get("callableJob")
        .start(callableStep())
        .build();
  }

  @Bean
  public Step callableStep() {
    return stepBuilderFactory.get("callableStep")
        .tasklet(tasklet())
        .build();
  }

  @Bean
  public CallableTaskletAdapter tasklet() {
    CallableTaskletAdapter callableTaskletAdapter = new CallableTaskletAdapter();
    callableTaskletAdapter.setCallable(callableObject());
    return callableTaskletAdapter;
  }

  @Bean
  public Callable<RepeatStatus> callableObject() {
    return () -> {
      System.out.println("이건 다른 스레드에서 실행됩니다.");
      return RepeatStatus.FINISHED;
    };
  }
}
