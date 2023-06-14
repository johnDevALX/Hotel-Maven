package com.ekene.hotelmanagement.model.hotel;

import com.ekene.hotelmanagement.enums.RoomType;
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
public class Room extends BaseModel {
    private String image;
    private String title;
    private double cost;
    private int bed;
    private int maxOccupants;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
//    private Availability availability;
    private Boolean isAvailable;
}
