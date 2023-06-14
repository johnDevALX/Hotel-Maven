package com.ekene.hotelmanagement.service.email;

import com.ekene.hotelmanagement.payload.Email;

public interface EmailService {
    void sendEmail(Email email);
}
