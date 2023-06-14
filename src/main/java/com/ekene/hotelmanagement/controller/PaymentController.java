package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.payload.PaymentRequest;
import com.ekene.hotelmanagement.response.payment.PaymentReturnResponse;
import com.ekene.hotelmanagement.service.payment.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    @PostMapping("init")
    public Map<String, Object> initializePayment(@RequestBody PaymentRequest paymentRequest){
        System.out.println("Email from paymentController class ----> "   + paymentRequest.getEmail());
        return paymentService.initialize(paymentRequest);
    }

    @GetMapping("verify")
    public PaymentReturnResponse verifyPayment(@RequestParam int transaction_id){
        return paymentService.verifyTransaction(transaction_id);
    }
}
