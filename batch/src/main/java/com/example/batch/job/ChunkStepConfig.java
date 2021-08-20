package com.example.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChunkStepConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job job() {
    return jobBuilderFactory.get("job")
        .start(step1())
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
        .<String, String>chunk(10)
        .reader(itemReader(null))
        .writer(itemWriter(null))
        .build();
  }

  @Bean
  public FlatFileItemReader<String> itemReader(@Value("#{#jobParameters['inputFile']}") Resource inputFile) {
    return new FlatFileItemReaderBuilder<String>()
        .name("itemReader")
        .resource(inputFile)
        .lineMapper(new PassThroughLineMapper())
        .build();
  }

  @Bean
  public FlatFileItemWriter<String> itemWriter(@Value("#{#jobParameters['outputFile']}") Resource outputFile){

  }
}
