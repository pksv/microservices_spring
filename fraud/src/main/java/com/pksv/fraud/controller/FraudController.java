package com.pksv.fraud.controller;

import com.pksv.fraud.model.response.FraudCheckResponse;
import com.pksv.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService service;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraud(@PathVariable("customerId") Integer customerId) {
        boolean isFraud = service.isFraudCustomer(customerId);
        log.info("fraud check for customer {}", customerId);
        return new FraudCheckResponse(isFraud);
    }
}
