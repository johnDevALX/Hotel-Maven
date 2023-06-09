package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.hotel.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
