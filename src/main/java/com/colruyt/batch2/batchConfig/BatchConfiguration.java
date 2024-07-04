package com.colruyt.batch2.batchConfig;

import com.colruyt.batch2.model.EmployeeModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Optional;

// todo : check package name
@Configuration
public class BatchConfiguration {
    // todo : is the file.input is coming from application.properties file?
    @Value("${file.input}")
    private String fileInput;

    // TODO: idea was to write JSON Reader not CSV Reader
    @Bean
    public FlatFileItemReader<com.colruyt.batch2.model.EmployeeModel> reader() {

        return new FlatFileItemReaderBuilder<com.colruyt.batch2.model.EmployeeModel>()
                .name("studentItemReader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names(new String[]{"id", "name", "salary", "email", "mobile"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<com.colruyt.batch2.model.EmployeeModel>() {{
                    setTargetType(com.colruyt.batch2.model.EmployeeModel.class); // todo : why using fully qualified class name?
                }})
                .build();
    }


    @Bean
    public JdbcBatchItemWriter<EmployeeModel> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<EmployeeModel>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO EMPLOYEE_DATA VALUES (:id, :name, :salary,:email,:mobile)")
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
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, JdbcBatchItemWriter<EmployeeModel> writer) {
        return new StepBuilder("Step", jobRepository)
                .<EmployeeModel, EmployeeModel>chunk(2, platformTransactionManager)
                .reader(reader())
                .writer(writer)
                .build();
    }

}
