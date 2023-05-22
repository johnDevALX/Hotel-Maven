package com.ekene.hotelmanagement.payload;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoomDto {
    private String title;
    private double cost;
    private int bed;
    private int maxOccupants;
    private String roomTypeDtoTitle;
}
