package com.ekene.hotelmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class CustomerOrder extends BaseModel{
    @OneToOne
    private Customer customer;
    @OneToOne
    private Room room;
    @OneToOne
    private ServiceBilling serviceBilling;
    private int numberOfItem;
}
