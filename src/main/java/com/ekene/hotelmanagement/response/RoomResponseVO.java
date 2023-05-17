package com.ekene.hotelmanagement.response;

import com.ekene.hotelmanagement.model.RoomType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RoomResponseVO {
    private String image;
    private String title;
    private RoomType roomType;
}
