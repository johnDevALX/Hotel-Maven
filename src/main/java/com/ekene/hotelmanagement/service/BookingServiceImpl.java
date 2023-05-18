package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.model.Booking;
import com.ekene.hotelmanagement.model.Room;
import com.ekene.hotelmanagement.repository.BookingRepository;
import com.ekene.hotelmanagement.repository.CustomerRepository;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.response.BookingResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

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
        room.setIsAvailable(false);
        roomRepository.save(room);
        return mapToBookingResponse(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Scheduled(cron = "59 59 23 * * ?")
    public void updateAvailability(){
        List<Booking> allBooking = bookingRepository.findAll();
        for (Booking booking: allBooking) {
            if (booking.getCheckOut().isBefore(ChronoLocalDateTime.from(LocalDate.now())))    {
                System.out.println(booking.getRoom().getTitle() + "Still Occupied");
            }else {
                Room room = roomRepository.findById(booking.getRoom().getId()).get();
                room.setIsAvailable(true);
            }
        }
    }

    private BookingResponseVO mapToBookingResponse(Booking booking) {
        return BookingResponseVO.builder()
                .roomTitle(booking.getRoom() == null? " " : booking.getRoom().getTitle())
                .fromDate(booking.getCheckIn())
                .toDate(booking.getCheckOut())
                .build();
    }
}
