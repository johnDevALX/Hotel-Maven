//package com.ekene.hotelmanagement.model;
//
//import com.ekene.hotelmanagement.model.Address;
//import com.ekene.hotelmanagement.controller.enums.Role;
//import com.ekene.hotelmanagement.model.BaseModel;
//import jakarta.persistence.*;
//import lombok.*;
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//@Entity
//public class Admin extends BaseModel {
//    private String firstName;
//    private String lastName;
//    @Column(unique = true)
//    private String email;
//    private String nationality;
//    @Embedded
//    private Address address;
//    private String password;
//    @Enumerated(EnumType.STRING)
//    private Role role;
//    private double salary;
//}
