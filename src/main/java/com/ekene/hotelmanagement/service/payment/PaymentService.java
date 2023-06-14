package com.ekene.hotelmanagement.service.payment;

import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.payload.PaymentRequest;
import com.ekene.hotelmanagement.response.payment.PaymentReturnResponse;
import org.springframework.ui.Model;

import java.util.Map;

public interface PaymentService {

    Map<String, Object> initialize(PaymentRequest paymentRequest);
    PaymentReturnResponse verifyTransaction(int transactionId);

}
