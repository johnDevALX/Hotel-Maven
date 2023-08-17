package com.ekene.hotelmanagement.service.customer;

import com.ekene.hotelmanagement.model.*;
import com.ekene.hotelmanagement.model.account.HotelAccount;
import com.ekene.hotelmanagement.model.hotel.CustomerOrder;
import com.ekene.hotelmanagement.model.hotel.Room;
import com.ekene.hotelmanagement.model.hotel.ServiceBilling;
import com.ekene.hotelmanagement.model.payment.PaymentDetails;
import com.ekene.hotelmanagement.payload.CustomerOrderDto;
import com.ekene.hotelmanagement.payload.Email;
import com.ekene.hotelmanagement.repository.*;
import com.ekene.hotelmanagement.response.CustomerOrderResponseVO;
import com.ekene.hotelmanagement.service.email.EmailServiceImpl;
import com.ekene.hotelmanagement.service.hotel.PaymentDetailsServiceImpl;
import com.ekene.hotelmanagement.service.payment.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ServiceBillingRepository serviceBillingRepository;
    private final PaymentServiceImpl paymentService;
    private final PaymentDetailsServiceImpl paymentDetailsService;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final EmailServiceImpl emailService;
    private final AccountRepository accountRepository;


    @Override
    public CustomerOrderResponseVO makeOrder(CustomerOrderDto orderDto) {
        HotelAccount hotelAccount = accountRepository.findById(1L).get();
        Users user = userRepository.findByEmailIgnoreCase(orderDto.getCustomerEmail()).get();
        Room room = roomRepository.findByTitleIgnoreCase(orderDto.getRoomTitle()).get();
        ServiceBilling serviceBilling = serviceBillingRepository.findByItemNameIgnoreCase(orderDto.getItemName()).get();
        CustomerOrder order = CustomerOrder.builder()
                .users(user)
                .room(room)
                .serviceBilling(serviceBilling)
                .numberOfItem(orderDto.getNumberOfItem())
                .build();

        if (validatePayment(orderDto.getPaymentId())){
            String description = "SERVICE ORDER";
            paymentDetailsService.savePaymentDetails(paymentService.verifyTransaction(orderDto.getPaymentId()).getData(), description);
            PaymentDetails paymentDetails = paymentDetailsRepository.findById(String.valueOf(orderDto.getPaymentId())).get();
            hotelAccount.setHotelAccountBal(
                    hotelAccount.getHotelAccountBal() + (paymentDetails.getAmount_settled() * order.getNumberOfItem()));
            accountRepository.save(hotelAccount);
            System.out.println("From Customer Order service Class ----> " + hotelAccount.getHotelAccountBal());

            String subject = "Hotel-Maven Service Order Transaction Alert";
            String text = ("Dear " + user.getFirstName() + "\nYour account has been debited "
                    + paymentDetails.getCharged_amount() + "\n\n\n\nDo enjoy the services rendered and enjoy you stay!!!");
            Email email = Email.builder()
                    .to(user.getEmail())
                    .subject(subject)
                    .text(text)
                    .build();
            customerOrderRepository.save(order);
            emailService.sendEmail(email);
            System.out.println("From Customer Order service Class, email delivered to  ----> " + user.getEmail());
        }
        return mapToOrderResponse(order);
    }

    @Override
    public String deleteOrder(Long id) {
        String response = "";
        try{
            customerOrderRepository.deleteById(id);
            response = "Successfully Deleted";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(response);
        }
        return response;
    }


    private CustomerOrderResponseVO mapToOrderResponse(CustomerOrder order){
        return CustomerOrderResponseVO.builder()
                .customerEmail(order.getUsers().getEmail())
                .roomTitle(order.getRoom().getTitle())
                .itemName(order.getServiceBilling().getItemName())
                .numberOfItem(order.getNumberOfItem())
                .build();
    }

    private Boolean validatePayment(int transactionId){
        return paymentService.verifyTransaction(transactionId)
                .getData().getProcessor_response().equalsIgnoreCase("successful");
    }
}
