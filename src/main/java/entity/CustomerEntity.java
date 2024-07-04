package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
