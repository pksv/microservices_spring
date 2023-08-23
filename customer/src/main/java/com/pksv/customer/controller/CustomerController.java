package com.pksv.customer.controller;

import com.pksv.customer.model.Customer;
import com.pksv.customer.model.request.CustomerRegistrationRequest;
import com.pksv.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        log.info("new Customer.... {}", request);
        customerService.registerCustomer(request);
    }

    @GetMapping
    @ResponseBody
    public Customer getCustomer(@RequestParam("email") String email) {
        log.info("email to search {}", email);
        return customerService.findCustomerEmail(email);
    }
}
