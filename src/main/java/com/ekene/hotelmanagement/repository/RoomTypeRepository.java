package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Optional<RoomType> findByTitleIgnoreCase(String title);
}
