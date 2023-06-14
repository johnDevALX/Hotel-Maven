package com.ekene.hotelmanagement.model.hotel;

import com.ekene.hotelmanagement.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class RoomType extends BaseModel {
    private String image;
    private String title;
}
