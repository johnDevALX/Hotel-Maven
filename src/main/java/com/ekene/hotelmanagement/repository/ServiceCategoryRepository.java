package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    Optional<ServiceCategory> findByTitleIgnoreCase(String title);
}
