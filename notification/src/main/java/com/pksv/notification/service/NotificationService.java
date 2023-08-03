package com.pksv.notification.service;

import com.pksv.clients.notification.NotificationRequest;
import com.pksv.notification.db.NotificationRepo;
import com.pksv.notification.model.Notification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepo repo;

    public void send(NotificationRequest notificationRequest) {
        repo.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .message(notificationRequest.message())
                        .sender(notificationRequest.from())
                        .sentTime(LocalDateTime.now())
                        .build()
        );
    }
}
