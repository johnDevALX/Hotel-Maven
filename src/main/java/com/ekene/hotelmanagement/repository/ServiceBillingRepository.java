package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.hotel.ServiceBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceBillingRepository extends JpaRepository<ServiceBilling, Long> {
    Optional<ServiceBilling> findByItemNameIgnoreCase(String itemName);
}
