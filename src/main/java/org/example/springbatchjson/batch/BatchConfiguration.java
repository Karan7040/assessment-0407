package org.example.springbatchjson.batch;

import org.example.springbatchjson.model.EmployeeDetail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfiguration {

    @Bean
    public ItemProcessor<EmployeeDetail, EmployeeDetail> itemProcessor() {
        return employeeDetail -> {
            return employeeDetail;
        };
    }

    @Bean
    public ItemWriter<EmployeeDetail> itemWriter() {
        return items -> {
            for (EmployeeDetail item : items) {
                System.out.println("Writing employee: " + item);
            }
        };
    }
    @Bean
    public Job job(JobRepository jobRepository, Step step){
        JobBuilder jobBuilder = new JobBuilder("Coding", jobRepository);
        return jobBuilder.start(step).build();

    }
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, JdbcBatchItemWriter jdbcBatchItemWriter){
        StepBuilder stepBuilder = new StepBuilder("Step", jobRepository);
        return stepBuilder.chunk(2, platformTransactionManager).reader((ItemReader<?>) itemProcessor()).writer(jdbcBatchItemWriter).build();
    }


}
