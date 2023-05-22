package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.model.*;
import com.ekene.hotelmanagement.payload.CustomerOrderDto;
import com.ekene.hotelmanagement.repository.CustomerRepository;
import com.ekene.hotelmanagement.repository.CustomerOrderRepository;
import com.ekene.hotelmanagement.repository.RoomRepository;
import com.ekene.hotelmanagement.repository.ServiceBillingRepository;
import com.ekene.hotelmanagement.response.CustomerOrderResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ServiceBillingRepository serviceBillingRepository;

    @Override
    public CustomerOrderResponseVO makeOrder(CustomerOrderDto orderDto) {
        Customer customer = customerRepository.findCustomerByEmailIgnoreCase(orderDto.getCustomerEmail()).get();
        Room room = roomRepository.findByTitleIgnoreCase(orderDto.getRoomTitle()).get();
        ServiceBilling serviceBilling = serviceBillingRepository.findByItemNameIgnoreCase(orderDto.getItemName()).get();
        CustomerOrder order = CustomerOrder.builder()
                .customer(customer)
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
                .customerEmail(order.getCustomer().getEmail())
                .roomTitle(order.getRoom().getTitle())
                .itemName(order.getServiceBilling().getItemName())
                .numberOfItem(order.getNumberOfItem())
                .build();
    }
}
