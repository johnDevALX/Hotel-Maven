package com.ekene.hotelmanagement.payload;

import com.ekene.hotelmanagement.enums.HotelService;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceBillingDto {
    private Long id;
    private HotelService hotelService;
    private String itemName;
    private Double itemCost;
}
