package com.pksv.notification.db;

import com.pksv.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
}
