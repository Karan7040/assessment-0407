package com.colruyt.springtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    private Integer id;
    private String name;
    private  Integer salary;
    private String email;
    private String mobile;
}
