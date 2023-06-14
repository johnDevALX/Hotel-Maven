package com.ekene.hotelmanagement.service.hotel;


import com.ekene.hotelmanagement.model.hotel.Booking;
import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.response.BookingResponseVO;

import java.util.List;

public interface BookingService {
    BookingResponseVO saveBooking(BookingDto bookingDto);
    List<Booking> getAllBookings ();
}
