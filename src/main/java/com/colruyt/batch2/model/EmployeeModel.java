
package com.colruyt.batch2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class EmployeeModel {

    private Integer id;

    private String name;

    private Integer salary;

    private String email;

    private String mobile;

}

