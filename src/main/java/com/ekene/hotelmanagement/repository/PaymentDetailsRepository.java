package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.payment.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, String> {
}
