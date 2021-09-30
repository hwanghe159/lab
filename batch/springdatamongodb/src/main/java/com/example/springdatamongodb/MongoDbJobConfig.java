package com.example.springdatamongodb;

import java.util.Collections;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class MongoDbJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job mongoDbJob() {
    return this.jobBuilderFactory.get("mongoDbJob")
        .start(mongoDbStep())
        .build();
  }

  @Bean
  public Step mongoDbStep() {
    return this.stepBuilderFactory.get("mongoDbStep")
        .<Map, Map>chunk(10)
        .reader(tweetsItemReader(null, null))
        .writer(itemWriter())
        .build();
  }

  @Bean
  @StepScope
  public ItemReader<? extends Map> tweetsItemReader(
      MongoOperations mongoTemplate, @Value("#{jobParameters['hashTag']}") String hashtag
  ) {
    return new MongoItemReaderBuilder<Map>()
        .name("tweetsItemReader")
        .targetType(Map.class)
        .jsonQuery("{ \"entities.hashtags.text\": {$eq: ?0 }}")
        .collection("tweets_collection")
        .parameterValues(Collections.singletonList(hashtag))
        .pageSize(10)
        .sorts(Collections.singletonMap("created_at", Direction.ASC))
        .template(mongoTemplate)
        .build();
  }

  @Bean
  public ItemWriter<Map> itemWriter() {
    return items -> items.forEach(System.out::println);
  }
}