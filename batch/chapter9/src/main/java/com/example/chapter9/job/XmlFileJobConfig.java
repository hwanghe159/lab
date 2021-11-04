package com.example.chapter9.job;

import com.example.chapter9.Customer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class XmlFileJobConfig {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private FlatFileItemReader<Customer> customerFileReader;
  private final Resource outputResource
      = new FileSystemResource("chapter9/output/customers");

  @Bean
  public Job xmlFileJob() {
    return this.jobBuilderFactory.get("xmlFileJob")
        .start(xmlFileStep())
        .incrementer(new RunIdIncrementer())
        .build();
  }

  @Bean
  public Step xmlFileStep() {
    return this.stepBuilderFactory.get("xmlFileStep")
        .<Customer, Customer>chunk(10)
        .reader(customerFileReader)
        .writer(xmlCustomerWriter())
        .build();
  }


  @Bean
  public StaxEventItemWriter<Customer> xmlCustomerWriter() {
    Map<String, Class<Customer>> aliases = new HashMap<>();
    aliases.put("customer", Customer.class);

    XStreamMarshaller marshaller = new XStreamMarshaller();
    marshaller.setAliases(aliases);

    return new StaxEventItemWriterBuilder<Customer>()
        .name("xmlOutputWriter")
        .resource(outputResource)
        .marshaller(marshaller)
        .rootTagName("customers")
        .build();
  }

  @Bean
  public Job classifierCompositeWriterJob() {
    return this.jobBuilderFactory.get("classifierCompositeWriterJob")
        .start(classifierCompositeWriterStep())
        .build();
  }

  @Bean
  public Step classifierCompositeWriterStep() {
    return this.stepBuilderFactory.get("classifierCompositeWriterStep")
        .<Customer, Customer>chunk(10)
        .reader(...)
				.writer(classifierCompositeItemWriter())
        .stream(xmlItemWriter())
        .build();
  }

  @Bean
  public ClassifierCompositeItemWriter<Customer> classifierCompositeItemWriter() {
    Classifier<Customer, ItemWriter<? super Customer>> classifier
        = new CustomerClassifier(xmlItemWriter(), jdbcItemWriter());

    return new ClassifierCompositeItemWriterBuilder<Customer>()
        .classifier(classifier)
        .build();
  }
}
