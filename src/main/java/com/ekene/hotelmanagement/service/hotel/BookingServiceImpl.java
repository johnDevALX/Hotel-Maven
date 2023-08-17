package com.ekene.hotelmanagement.service.hotel;

import com.ekene.hotelmanagement.model.account.HotelAccount;
import com.ekene.hotelmanagement.model.hotel.Booking;
import com.ekene.hotelmanagement.model.hotel.Room;
import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.model.payment.PaymentDetails;
import com.ekene.hotelmanagement.payload.Email;
import com.ekene.hotelmanagement.repository.*;
//import com.ekene.hotelmanagement.repository.CustomerRepository;
import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.response.BookingResponseVO;
import com.ekene.hotelmanagement.service.email.EmailServiceImpl;
import com.ekene.hotelmanagement.service.payment.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final PaymentServiceImpl paymentService;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final PaymentDetailsServiceImpl paymentDetailsService;
    private final EmailServiceImpl emailService;
    private final AccountRepository accountRepository;



    @Override
    @Transactional
    public BookingResponseVO saveBooking(BookingDto bookingDto) {
        HotelAccount hotelAccount = accountRepository.findById(1L).get();
        Users user = userRepository.findByEmailIgnoreCase(bookingDto.getCustomerEmail()).get();
        Room room1 = roomRepository.findByTitleIgnoreCase(bookingDto.getRoomTitle()).get();
        Booking booking = Booking.builder()
                .user(user)
                .room(room1)
                .checkIn(bookingDto.getCheckIn())
                .checkOut(bookingDto.getCheckOut())
                .totalDays(bookingDto.getCheckOut().getDayOfWeek().getValue() - bookingDto.getCheckIn().getDayOfWeek().getValue())
                .build();
        log.info("Booking=========> {}", booking);
         bookingRepository.save(booking);
        Room room = roomRepository.findByTitleIgnoreCase(bookingDto.getRoomTitle()).get();
        if (validatePayment(bookingDto.getPaymentId())){
            room.setIsAvailable(false);
            String description = "BOOKING";
            paymentDetailsService.savePaymentDetails(paymentService.verifyTransaction(bookingDto.getPaymentId()).getData(), description);
            PaymentDetails paymentDetails = paymentDetailsRepository.findById(String.valueOf(bookingDto.getPaymentId())).get();
            hotelAccount.setHotelAccountBal(
                    hotelAccount.getHotelAccountBal() + paymentDetails.getAmount_settled());
            accountRepository.save(hotelAccount);
            System.out.println("From Booking service Class ----> " + hotelAccount.getHotelAccountBal());

            String subject = "Hotel-Maven Booking Transaction Alert";
            String text = ("Dear " + user.getFirstName() + "\nYour account has been debited "
                    + paymentDetails.getCharged_amount() + "\n\n\n\nnKindly complete your booking process and enjoy you stay!!!");
            Email email = Email.builder()
                    .to(user.getEmail())
                    .subject(subject)
                    .text(text)
                    .build();
            emailService.sendEmail(email);

            System.out.println("From Booking service Class, email delivered to  ----> " + user.getEmail());
        }
        roomRepository.save(room);
        return mapToBookingResponse(booking);
    }


    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Scheduled(cron = "0 */59 * ? * *")
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

    @Scheduled(cron = "0 * * * * *")
    public void preCheckOutMail(){
        List<Booking> bookingList = bookingRepository.findAll();
        for (Booking booking: bookingList) {
            if (LocalDateTime.now().equals(booking.getCheckOut().minusHours(2))){
                String subject = "Hotel-Maven Pre CheckOut Alert";
                String text = ("Dear " + booking.getUser().getFirstName() + "\nYour Check Out Time Is At " +
                        booking.getCheckOut() + "\n\n\n\nPlease let us know if you need any assistance with your luggage" +
                        "or any questions before you depart!!!");

                Email email = Email.builder()
                        .to(booking.getUser().getEmail())
                        .subject(subject)
                        .text(text)
                        .build();
                emailService.sendEmail(email);

                System.out.println("From Booking service Class Pre check out, email delivered to  ----> " + booking.getUser().getEmail());
            }
        }
    }
}
