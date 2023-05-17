package com.ekene.hotelmanagement.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoomDto {
    private Long id;
    private String image;
    private String title;
    private double cost;
    private int bed;
    private int maxOccupants;
    private RoomTypeDto roomTypeDto;
}
