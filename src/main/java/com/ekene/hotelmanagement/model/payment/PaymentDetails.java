package com.ekene.hotelmanagement.model.payment;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class PaymentDetails{
    @Id
    private String id;
    private double charged_amount;
    private String processor_response;
    private String payment_type;
    private double amount_settled;
    private String payment_description;
}
