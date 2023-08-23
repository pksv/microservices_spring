package com.pksv.customer.service;

import com.pksv.amqp.RabbitMQMessageProducer;
import com.pksv.clients.fraud.FraudCheckResponse;
import com.pksv.clients.fraud.FraudClient;
import com.pksv.clients.notification.NotificationRequest;
import com.pksv.customer.db.CustomerRepo;
import com.pksv.customer.model.Customer;
import com.pksv.customer.model.request.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .fName(request.fName())
                .lName(request.lName())
                .email(request.email())
                .build();
        repo.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());
        if (fraudCheckResponse.isFraud()) {
            throw new IllegalStateException("fraud");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "pksv",
                String.format("Hi %s, welcome", customer.getFName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-keys"
        );
    }

    public Customer findCustomerEmail(String email) {
        List<Customer> customers = repo.findCustomerByEmail(email);
        return customers.get(0);
    }
}
