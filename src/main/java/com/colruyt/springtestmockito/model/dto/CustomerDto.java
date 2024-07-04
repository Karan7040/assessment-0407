package com.colruyt.springtestmockito.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer custId;
    private String address;
    private String firstName;
    private String lastName;
}
