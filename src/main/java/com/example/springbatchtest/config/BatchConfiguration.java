package com.example.springbatchtest.config;

import com.example.springbatchtest.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration

public class BatchConfiguration {
    @Value("${file.input}")
    private String fileInput;


    @Bean
    public JsonItemReader<Employee> reader() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonJsonObjectReader<Employee> jacksonJsonObjectReader = new JacksonJsonObjectReader<>(Employee.class);
        jacksonJsonObjectReader.setMapper(objectMapper);
        return new JsonItemReaderBuilder<Employee>()
                .jsonObjectReader(jacksonJsonObjectReader)
                .resource(new ClassPathResource(fileInput))
                .name("reader")
                .build();
    }


    @Bean
    public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        JdbcBatchItemWriterBuilder<Employee> writer = new JdbcBatchItemWriterBuilder<Employee>();
        return writer.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO EMPLOYEE_DATA VALUES(:id,:name,:salary,:email,:mobile")
                .dataSource(dataSource)
                .build();

    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        JobBuilder jobBuilder = new JobBuilder("Coding", jobRepository);
        return jobBuilder.start(step).build();

    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, DataSource dataSource) {
        StepBuilder stepBuilder = new StepBuilder("StepBuilder", jobRepository);
        return stepBuilder.<Employee, Employee>chunk(2, platformTransactionManager)
                .reader(reader())
                .writer(writer(dataSource)).build();
    }
}
