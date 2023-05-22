package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.model.Booking;
import com.ekene.hotelmanagement.model.Customer;
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
import java.time.LocalDateTime;
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
        Customer customer = customerRepository.findCustomerByEmailIgnoreCase(bookingDto.getCustomerEmail()).get();
        Room room1 = roomRepository.findByTitleIgnoreCase(bookingDto.getRoomTitle()).get();
        Booking booking = Booking.builder()
                .customer(customer)
                .room(room1)
                .checkIn(bookingDto.getCheckIn())
                .checkOut(bookingDto.getCheckOut())
                .totalDays(bookingDto.getCheckOut().getDayOfWeek().getValue() - bookingDto.getCheckIn().getDayOfWeek().getValue())
                .build();
         bookingRepository.save(booking);
        Room room = roomRepository.findByTitleIgnoreCase(bookingDto.getRoomTitle()).get();
        room.setIsAvailable(false);
        roomRepository.save(room);
        return mapToBookingResponse(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Scheduled(cron = "0 0 */12 ? * *")
    public void updateAvailability(){

        System.out.println("Print========================>>>>>>>" + LocalDateTime.now());
        List<Booking> allBooking = bookingRepository.findAll();
        for (Booking booking: allBooking) {
            System.out.println(booking.getCheckOut());
            if (booking.getCheckOut().isBefore(LocalDateTime.now()))    {
                Room room = roomRepository.findById(booking.getRoom().getId()).get();
                room.setIsAvailable(true);
                roomRepository.save(room);
                System.out.println("Checked Out" + LocalDateTime.now());
            }else {
                System.out.println(booking.getRoom().getTitle() + "Still Occupied");
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
