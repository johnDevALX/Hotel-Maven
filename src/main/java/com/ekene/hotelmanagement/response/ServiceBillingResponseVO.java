package com.ekene.hotelmanagement.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class ServiceBillingResponseVO {
    private String itemName;
    private Double itemCost;
}
