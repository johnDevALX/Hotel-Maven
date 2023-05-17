package com.ekene.hotelmanagement.payload;

import com.ekene.hotelmanagement.model.Customer;
import com.ekene.hotelmanagement.model.Room;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookingDto {
    private Customer customer;
    private Room room;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int totalDays;

}
