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
// todo : no need suffix of Model.. it is already a model , better suffix it with entity
public class CustomerModel {
    @Id
    private Long customerId;
    private String address;
    private String firstName;
    private String lastName;
}
