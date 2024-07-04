package com.example.springbatch54yh.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private String id;
    private String name;
    private String salary;
    private String email;
    private Integer mobile;
}
