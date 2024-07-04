package com.example.springbatchexam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// todo  : are we using Builder? if not then remove @Builder. Also @AllArgsConstructor, @NoArgsConstructor if not used remove them
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    Integer id;
    String name;
    String salary;
    String email;
    String phoneno; // todo : use phoneNumber
}
