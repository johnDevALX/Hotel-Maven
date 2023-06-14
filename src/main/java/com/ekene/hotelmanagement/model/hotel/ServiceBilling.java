package com.ekene.hotelmanagement.model.hotel;

import com.ekene.hotelmanagement.enums.HotelService;
import com.ekene.hotelmanagement.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class ServiceBilling extends BaseModel {
    private Long id;
//    @OneToOne(cascade = CascadeType.ALL)
//    private ServiceCategory serviceCategory;
    @Enumerated(EnumType.STRING)
    private HotelService hotelService;
    private String itemName;
    private Double itemCost;
}
