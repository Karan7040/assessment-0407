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
// todo: if the job of processor is just to return what is being passed as input.. then what's the point of having it?
    @Bean
    public ItemProcessor<EmployeeDetail, EmployeeDetail> itemProcessor() {
        return employeeDetail -> {
            return employeeDetail;
        };
    }
// todo : the idea was to write the data to the database.. but it's not happening.. why?
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
    // todo : bad code. 1. Type casting a processor into item reader? 2. JdbcBatchItemWriter why when no bean of that type?
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, JdbcBatchItemWriter jdbcBatchItemWriter){
        StepBuilder stepBuilder = new StepBuilder("Step", jobRepository);
        return stepBuilder.chunk(2, platformTransactionManager).reader((ItemReader<?>) itemProcessor()).writer(jdbcBatchItemWriter).build();
    }


}
