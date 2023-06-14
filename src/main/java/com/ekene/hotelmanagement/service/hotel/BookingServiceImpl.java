package com.ekene.hotelmanagement.service.hotel;

import com.ekene.hotelmanagement.model.account.HotelAccount;
import com.ekene.hotelmanagement.model.hotel.Booking;
import com.ekene.hotelmanagement.model.hotel.Room;
import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.model.payment.PaymentDetails;
import com.ekene.hotelmanagement.repository.BookingRepository;
//import com.ekene.hotelmanagement.repository.CustomerRepository;
import com.ekene.hotelmanagement.repository.PaymentDetailsRepository;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.response.BookingResponseVO;
import com.ekene.hotelmanagement.service.payment.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final PaymentServiceImpl paymentService;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final PaymentDetailsServiceImpl paymentDetailsService;



    @Override
    @Transactional //TODO reminder
    public BookingResponseVO saveBooking(BookingDto bookingDto) {
        HotelAccount hotelAccount = HotelAccount.getInstance();
        Users user = userRepository.findByEmailIgnoreCase(bookingDto.getCustomerEmail()).get();
        Room room1 = roomRepository.findByTitleIgnoreCase(bookingDto.getRoomTitle()).get();
        Booking booking = Booking.builder()
                .user(user)
                .room(room1)
                .checkIn(bookingDto.getCheckIn())
                .checkOut(bookingDto.getCheckOut())
                .totalDays(bookingDto.getCheckOut().getDayOfWeek().getValue() - bookingDto.getCheckIn().getDayOfWeek().getValue())
                .build();
         bookingRepository.save(booking);
        Room room = roomRepository.findByTitleIgnoreCase(bookingDto.getRoomTitle()).get();
        if (validatePayment(bookingDto.getPaymentId())){
            room.setIsAvailable(false);
            paymentDetailsService.savePaymentDetails(paymentService.verifyTransaction(bookingDto.getPaymentId()).getData());
            PaymentDetails paymentDetails = paymentDetailsRepository.findById(String.valueOf(bookingDto.getPaymentId())).get();
            hotelAccount.setHotelAccountBal(
                    hotelAccount.getHotelAccountBal() + paymentDetails.getAmount_settled());
            System.out.println(hotelAccount.getHotelAccountBal());
        }
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

    private Boolean validatePayment(int transactionId){
        return paymentService.verifyTransaction(transactionId)
                .getData().getProcessor_response().equalsIgnoreCase("successful");
    }
}
