package com.example.test_4_7_24.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {
    // todo: use camelCase and why to put customer redundantly?
    @Id
    @Column(name = "CUST_ID")
    private Integer customerid;
    @Column(name = "ADDRESS")
    private String customeraddress;
    @Column(name = "FIRST_NAME")
    private String customerfirstname;
    @Column(name = "LAST_NAME")
    private String customerlastname;


}
