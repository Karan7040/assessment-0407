package org.example.springtest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    // todo: use either customerId or id;
        private Integer custId;
        private String address;
        private String firstName;
        private String lastName;
 }

