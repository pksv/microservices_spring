package com.pksv.clients.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,

        String from,
        String message
) {
}
