package com.ekene.hotelmanagement.repository;

import com.ekene.hotelmanagement.model.User;
import com.ekene.hotelmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailIgnoreCase(String email);
}
