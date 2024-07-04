package com.example.springbatch_7j8m.config;
import com.example.springbatch_7j8m.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
// todo: why @EnableBatchProcessing if using spring version >=3?
@EnableBatchProcessing
@Configuration
public class BatchConfiguration {


    @Bean
    public JsonItemReader<Employee> reader() {

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonJsonObjectReader<Employee> jsonObjectReader =
                new JacksonJsonObjectReader<>(Employee.class);
        jsonObjectReader.setMapper(objectMapper);

        return new JsonItemReaderBuilder<Employee>()
                .jsonObjectReader(jsonObjectReader)
                .resource(new ClassPathResource("employee.json")).name("employeeJsonItemReader")
                .build();
    }




    @Bean
    public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        JdbcBatchItemWriterBuilder<Employee> jdbcBatchItemWriterBuilder = new JdbcBatchItemWriterBuilder<>();
        return jdbcBatchItemWriterBuilder.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("Insert into EMPLOYEE_DATA values(:id,:name,:salary,:email,:mobile)"
                ).dataSource(dataSource).build();

    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager platformTransactionManager,
                     DataSource dataSource) {
        StepBuilder stepBuilder = new StepBuilder("Step", jobRepository);
        return stepBuilder.<Employee, Employee>chunk(2, platformTransactionManager)
                .reader(reader()).writer(writer(dataSource)).build();

    }



    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("importUserJob", jobRepository)
                .start(step)
                .build();
    }


}
