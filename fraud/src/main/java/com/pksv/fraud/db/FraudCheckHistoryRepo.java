package com.pksv.fraud.db;

import com.pksv.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckHistoryRepo extends JpaRepository<FraudCheckHistory, Integer> {
}
