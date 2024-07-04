package com.colruyt.springtest.batch;

import com.colruyt.springtest.model.EmployeeModel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String fileInput;
    @Bean
    public JsonItemReader<EmployeeModel> readDetails() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonJsonObjectReader<EmployeeModel> jsonObjectReader = new JacksonJsonObjectReader<>(EmployeeModel.class);
        jsonObjectReader.setMapper(objectMapper);

        return new JsonItemReaderBuilder<EmployeeModel>()
                .jsonObjectReader(jsonObjectReader)
                .resource(new ClassPathResource(fileInput))
                .name("employeeJsonItemReader")
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<EmployeeModel> writeDetails(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<EmployeeModel>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(("INSERT INTO employee_data VALUES (:id, :name, :salary,:email, :mobile)"))
                .dataSource(dataSource)
                .build();
    }

//    @Bean
//    public FlatFileItemReader<EmployeeModel> readDetails(){
//        FlatFileItemReaderBuilder flatFileItemReaderBuilder = new FlatFileItemReaderBuilder<>();
//        flatFileItemReaderBuilder.resource(new ClassPathResource(fileInput))
//                .name("reader")
//                .delimited()
//                .names(new String[]{"id","name","salary","email","mobile"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
//                    setTargetType(EmployeeModel.class);
//                    }
//                }).build();
//
//        return flatFileItemReaderBuilder.build();
//    }
//
//   @Bean
//    public JdbcBatchItemWriter<EmployeeModel> writeDetails(DataSource dataSource) {
//        JdbcBatchItemWriterBuilder writerBuilder = new JdbcBatchItemWriterBuilder();
//       return writerBuilder.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO employee_data VALUES (:id, :name, :salary,:email, :mobile)").dataSource(dataSource).build();
//
//    }

    @Bean
    public Job job(JobRepository jobRepository,Step step){
        JobBuilder jobBuilder =new JobBuilder("qwerty",jobRepository);
        return  jobBuilder.start(step).build();

    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,DataSource dataSource){
        StepBuilder stepBuilder=new StepBuilder("jvvjvjg",jobRepository);
        return stepBuilder.<EmployeeModel,EmployeeModel>chunk(1,platformTransactionManager)
                .reader(readDetails()).writer(writeDetails(dataSource))
                .build();


    }



}
