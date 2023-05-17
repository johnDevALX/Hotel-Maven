package com.ekene.hotelmanagement.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Customer extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private LocalDate DOB;
    private Long mobile;
    private String nationality;
    @Embedded
    private Address address;
}
