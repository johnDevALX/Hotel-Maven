package com.ekene.hotelmanagement.model;

import com.ekene.hotelmanagement.enums.Availability;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private RoomType roomType;
    @Enumerated(EnumType.STRING)
    private Availability availability;
}
