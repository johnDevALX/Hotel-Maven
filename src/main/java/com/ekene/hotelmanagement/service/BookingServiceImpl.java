package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.enums.Availability;
import com.ekene.hotelmanagement.model.Booking;
import com.ekene.hotelmanagement.model.Room;
import com.ekene.hotelmanagement.repository.BookingRepository;
import com.ekene.hotelmanagement.repository.CustomerRepository;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.response.BookingResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService{
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;



    private BookingResponseVO mapToBookingResponse(Booking booking) {
        return BookingResponseVO.builder()
                .roomTitle(booking.getRoom() == null? " " : booking.getRoom().getTitle())
                .fromDate(booking.getCheckIn())
                .toDate(booking.getCheckOut())
                .build();
    }
}
