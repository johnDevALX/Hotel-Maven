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

    @Override
    public BookingResponseVO saveBooking(BookingDto bookingDto) {
        Booking booking = Booking.builder()
                .customer(bookingDto.getCustomer())
                .room(bookingDto.getRoom())
                .checkIn(bookingDto.getCheckIn())
                .checkOut(bookingDto.getCheckOut())
                .totalDays(bookingDto.getCheckOut().getDayOfWeek().getValue() - bookingDto.getCheckIn().getDayOfWeek().getValue())
                .build();
         bookingRepository.save(booking);
        Room room = roomRepository.findById(bookingDto.getRoom().getId()).get();
        room.setAvailability(Availability.OCCUPIED);
        roomRepository.save(room);
        return mapToBookingResponse(booking);

    }

    private BookingResponseVO mapToBookingResponse(Booking booking) {
        return BookingResponseVO.builder()
                .roomTitle(booking.getRoom() == null? " " : booking.getRoom().getTitle())
                .fromDate(booking.getCheckIn())
                .toDate(booking.getCheckOut())
                .build();
    }
}
