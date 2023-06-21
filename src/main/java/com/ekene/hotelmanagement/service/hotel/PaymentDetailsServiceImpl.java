package com.ekene.hotelmanagement.service.hotel;

import com.ekene.hotelmanagement.model.payment.PaymentDetails;
import com.ekene.hotelmanagement.repository.PaymentDetailsRepository;
import com.ekene.hotelmanagement.response.payment.PaymentData;
import com.ekene.hotelmanagement.service.payment.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService{
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public void savePaymentDetails(PaymentData paymentData, String desc) {
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .id(paymentData.getId())
                .amount_settled(paymentData.getAmount_settled())
                .payment_type(paymentData.getPayment_type())
                .charged_amount(paymentData.getCharged_amount())
                .processor_response(paymentData.getProcessor_response())
                .payment_description(desc)
                .build();
        paymentDetailsRepository.save(paymentDetails);
    }
}
