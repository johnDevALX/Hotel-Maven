package com.ekene.hotelmanagement.model.hotel;

import com.ekene.hotelmanagement.model.BaseModel;
import com.ekene.hotelmanagement.model.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class CustomerOrder extends BaseModel {
    @ManyToOne
    private Users users;
    @ManyToOne
    private Room room;
    @ManyToOne
    private ServiceBilling serviceBilling;
    private int numberOfItem;
}
