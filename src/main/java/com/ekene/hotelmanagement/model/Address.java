package com.ekene.hotelmanagement.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
