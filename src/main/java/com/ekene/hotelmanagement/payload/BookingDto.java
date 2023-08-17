package com.ekene.hotelmanagement.payload;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookingDto {
    private int paymentId;
    private String customerEmail;
    private String roomTitle;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
