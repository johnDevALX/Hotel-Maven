package com.ekene.hotelmanagement.service.customer;

import com.ekene.hotelmanagement.model.*;
import com.ekene.hotelmanagement.model.hotel.CustomerOrder;
import com.ekene.hotelmanagement.model.hotel.Room;
import com.ekene.hotelmanagement.model.hotel.ServiceBilling;
import com.ekene.hotelmanagement.payload.CustomerOrderDto;
import com.ekene.hotelmanagement.repository.CustomerOrderRepository;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.repository.ServiceBillingRepository;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.response.CustomerOrderResponseVO;
import com.ekene.hotelmanagement.service.customer.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ServiceBillingRepository serviceBillingRepository;

    @Override
    public CustomerOrderResponseVO makeOrder(CustomerOrderDto orderDto) {
        Users user = userRepository.findByEmailIgnoreCase(orderDto.getCustomerEmail()).get();
        Room room = roomRepository.findByTitleIgnoreCase(orderDto.getRoomTitle()).get();
        ServiceBilling serviceBilling = serviceBillingRepository.findByItemNameIgnoreCase(orderDto.getItemName()).get();
        CustomerOrder order = CustomerOrder.builder()
                .users(user)
                .room(room)
                .serviceBilling(serviceBilling)
                .numberOfItem(orderDto.getNumberOfItem())
                .build();

        customerOrderRepository.save(order);
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
}
