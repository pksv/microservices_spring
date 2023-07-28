package com.pksv.fraud.service;

import com.pksv.fraud.db.FraudCheckHistoryRepo;
import com.pksv.fraud.model.FraudCheckHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepo repo;

    public boolean isFraudCustomer(Integer customerId) {
        repo.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraud(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
