package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.account.HotelAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<HotelAccount, Long> {
}
