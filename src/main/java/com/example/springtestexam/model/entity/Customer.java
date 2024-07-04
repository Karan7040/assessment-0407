package com.example.springtestexam.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "CUSTOMER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
// todo : I hope we are using all the annotations in our code. if not remove them
    // todo : keep customerId or id
    @Id
    @Column(name = "CUST_ID", nullable = false)
    private String custId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

}
