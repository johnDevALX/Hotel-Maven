package com.ekene.hotelmanagement.payload;


import com.ekene.hotelmanagement.enums.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Date DOB;
    private int mobile;
    private String nationality;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String password;
    private Role role;
    private double salary;
}
