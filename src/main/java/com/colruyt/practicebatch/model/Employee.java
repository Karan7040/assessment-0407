package com.colruyt.practicebatch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Employee {
    private Integer id;
    private String name;
    private Integer salary;
    private String email;
    private String mobile;
}
