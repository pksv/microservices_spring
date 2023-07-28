package com.pksv.customer.model.request;

public record CustomerRegistrationRequest(
        String fName,
        String lName,
        String email
) {

}
