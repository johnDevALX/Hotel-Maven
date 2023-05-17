package com.ekene.hotelmanagement.service;


import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.response.BookingResponseVO;

public interface BookingService {
    BookingResponseVO saveBooking(BookingDto bookingDto);
}
