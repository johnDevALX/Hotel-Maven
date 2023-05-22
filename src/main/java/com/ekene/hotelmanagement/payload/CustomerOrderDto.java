package com.ekene.hotelmanagement.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerOrderDto {
    private Long id;
    private String customerEmail;
    private String roomTitle;
    private String itemName;
    private int numberOfItem;
}
