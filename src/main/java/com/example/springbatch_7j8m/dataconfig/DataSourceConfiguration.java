package com.example.springbatch_7j8m.dataconfig;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2config(){
        return new DataSourceProperties();
    }
    @Bean
    @BatchDataSource
    public DataSource h2DataSource(){
        return h2config().initializeDataSourceBuilder().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSourceProperties oracleconfig(){
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource oracledatasource(){
        return oracleconfig().initializeDataSourceBuilder().build();
    }
}
