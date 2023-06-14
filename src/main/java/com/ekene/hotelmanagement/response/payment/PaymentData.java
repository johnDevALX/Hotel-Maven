package com.ekene.hotelmanagement.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentData implements Serializable {
    private String id;
    private double charged_amount;
    private String processor_response;
    private String payment_type;
    private double amount_settled;
}
