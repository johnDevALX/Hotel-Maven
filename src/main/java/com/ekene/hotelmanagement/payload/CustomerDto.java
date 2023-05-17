package com.ekene.hotelmanagement.payload;

import com.ekene.hotelmanagement.model.Address;
import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private LocalDate DOB;
    private Long mobile;
    private String nationality;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
