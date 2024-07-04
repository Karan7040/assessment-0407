package com.colruyt.practicemockito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")

public class CustomerModel {
    @Id
    private Long customerId;
    private String address;
    private String firstName;
    private String lastName;
}
