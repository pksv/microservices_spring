package com.pksv.customer.service;

import com.pksv.customer.db.CustomerRepo;
import com.pksv.customer.model.Customer;
import com.pksv.customer.model.request.CustomerRegistrationRequest;
import com.pksv.customer.model.response.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .fName(request.fName())
                .lName(request.lName())
                .email(request.email())
                .build();
        repo.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse == null) {
            throw new NullPointerException();
        }
        if (fraudCheckResponse.isFraud()) {
            throw new IllegalStateException("fraud");
        }
    }
}
