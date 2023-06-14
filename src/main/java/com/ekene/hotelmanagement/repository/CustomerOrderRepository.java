package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.hotel.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
