package com.example.test_4_7_24.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Integer customerid;
    private String customeraddress;
    private String customerfirstname;
    private String customerlastname;
}
