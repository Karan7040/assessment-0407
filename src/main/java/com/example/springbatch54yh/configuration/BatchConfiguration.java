package com.example.springbatch54yh.configuration;

import com.example.springbatch54yh.model.EmployeeModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.JsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing

public class BatchConfiguration {


    @Bean
    public JsonItemReader<EmployeeModel> jsonItemReader() {
        JsonObjectReader<EmployeeModel> jsonObjectReader = new JacksonJsonObjectReader<>(EmployeeModel.class);
        return new JsonItemReaderBuilder<EmployeeModel>()
                .jsonObjectReader(jsonObjectReader)
                .resource(new FileSystemResource("com.example.springbatch54yh.resources.demo.json"))
                .name("jsonItemReader")
                .build();
    }

    @Bean
    public ItemWriter<EmployeeModel> itemWriter(DataSource dataSource) {
        return items -> {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            for (EmployeeModel item : items) {
                jdbcTemplate.update("INSERT INTO EMPLOYEE_DATA VALUES (:id, :name,:salary,:email,:mobileno)")
                        .databsource(dataSource)
                        .build();
            }
        };
    }


    @Bean
    public ItemProcessor<EmployeeModel, EmployeeModel> itemProcessor() {
        return new CompositeItemProcessorBuilder<EmployeeModel, EmployeeModel>()
                .delegates(new MyEntityProcessor())
                .build();
    }

    @Bean
    public Job jsonFileToDatabaseJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                                     JsonItemReader<EmployeeModel> jsonItemReader, ItemProcessor<EmployeeModel, EmployeeModel> itemProcessor,
                                     ItemWriter<EmployeeModel> itemWriter) {

        Step step = stepBuilderFactory.get("json-file-to-database-step")
                .<EmployeeModel, EmployeeModel>chunk(10)
                .reader(jsonItemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("json-file-to-database-job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }



}
