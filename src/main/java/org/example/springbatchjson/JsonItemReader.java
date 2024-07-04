package org.example.springbatchjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springbatchjson.model.EmployeeDetail;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// todo: OK, created the reader.. now what it should do?

@Component
@StepScope
public class JsonItemReader implements ItemReader<EmployeeDetail> {

    private BufferedReader bufferedReader;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${json.input.file}")
    private Resource inputFile;

    @Override
    public EmployeeDetail read() throws Exception {
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputFile.getInputStream()));
        }

        String line = bufferedReader.readLine();
        if (line != null) {
            return objectMapper.readValue(line, EmployeeDetail.class);
        } else {
            bufferedReader.close();
            return null;
        }
    }
}

