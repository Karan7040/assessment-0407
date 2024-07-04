package com.example.springtestexam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// todo : I hope we are using all the annotations in our code. if not remove them
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    // todo: make customeId or id
    private String custId;

    private String address;

    private String firstName;

    private String lastName;
}
