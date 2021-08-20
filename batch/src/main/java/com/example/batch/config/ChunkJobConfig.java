package com.example.batch.config;

import java.util.concurrent.CompletionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChunkJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job chunkBasedJob() {
    return jobBuilderFactory.get("chunkBasedJob")
        .start(chunkStep())
        .build();
  }

  @Bean
  public Step chunkStep() {
    return stepBuilderFactory.get("chunkStep")
        .<String, String>chunk(completionPolicy())
        .reader(itemReader())
        .writer(itemWriter())
        .listener()
        .build();
  }

  @Bean
  public CompletionPolicy completionPolicy(){
    CompositeCompletionPolicy policy = new CompositeCompletionPolicy();
    policy.setPolicies(new CompletionPolicy[]{
        new TimeoutTerminationPolicy(3), new SimpleCompletionPolicy(1000)
    });
    return policy;
  }

  @Bean
  public ListItemReader<String> itemReader() {
    return null;
  }

  @Bean
  public ListItemWriter<String> itemWriter() {
    return null;
  }

}
