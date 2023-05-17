package com.ekene.hotelmanagement.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class BookingResponseVO {
    private String roomTitle;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
