package com.ekene.hotelmanagement.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceBillingDto {
    private Long id;
    private String serviceCategory;
    private String itemName;
    private Double itemCost;
}
