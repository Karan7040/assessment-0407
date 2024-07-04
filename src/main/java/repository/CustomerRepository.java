package repository;

import entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.relational.core.sql.In;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

}