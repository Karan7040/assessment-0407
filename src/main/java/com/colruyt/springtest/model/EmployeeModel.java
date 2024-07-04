package com.colruyt.springtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// todo : it is already a model. then why again suffixing with model
public class EmployeeModel {
    private Integer id;
    private String name;
    private  Integer salary;
    private String email;
    private String mobile;
}
