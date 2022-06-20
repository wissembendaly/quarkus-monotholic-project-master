package org.starterProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.starterProj.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findAllByEnabled(Boolean enabled);
}
