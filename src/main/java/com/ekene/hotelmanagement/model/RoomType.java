package com.ekene.hotelmanagement.model;

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
