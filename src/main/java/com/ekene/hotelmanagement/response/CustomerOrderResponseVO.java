package com.ekene.hotelmanagement.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class CustomerOrderResponseVO {
    private String customerEmail;
    private String roomTitle;
    private String itemName;
    private int numberOfItem;
}
