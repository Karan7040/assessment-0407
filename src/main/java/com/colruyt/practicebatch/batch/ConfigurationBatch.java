package com.colruyt.practicebatch.batch;

import com.colruyt.practicebatch.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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

public class ConfigurationBatch {
    @Value("${file.input}")
    private String fileInput;

    @Bean
    public JsonItemReader<Employee> reader() {
        return new JsonItemReaderBuilder<Employee>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Employee.class))
                .resource(new ClassPathResource(fileInput))
                .name("EmployeeJsonItemReader")
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO employee_Data  VALUES (:id, :name, :salary, :email, :mobile)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("Job", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, JdbcBatchItemWriter<Employee> writer) {
        return new StepBuilder("Step", jobRepository)
                .<Employee, Employee>chunk(2, platformTransactionManager)
                .reader(reader())
                .writer(writer)
                .build();
    }
}
