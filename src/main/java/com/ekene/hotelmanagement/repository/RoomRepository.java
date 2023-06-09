package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByTitleIgnoreCase(String roomTitle);
}
