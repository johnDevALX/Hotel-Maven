package com.ekene.hotelmanagement.model;

import com.ekene.hotelmanagement.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User extends BaseModel {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String gender;
    private LocalDate DOB;
    private int mobile;
    private String nationality;
    @Embedded
    private Address address;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private double salary;
}
