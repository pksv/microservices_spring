package com.pksv.customer.db;

import com.pksv.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomerByEmail(String email);
}
