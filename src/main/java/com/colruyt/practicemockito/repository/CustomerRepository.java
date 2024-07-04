package com.colruyt.practicemockito.repository;

import com.colruyt.practicemockito.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<CustomerModel,Long> {
}
