package com.ekene.hotelmanagement.service.customer;

import com.ekene.hotelmanagement.payload.CustomerOrderDto;
import com.ekene.hotelmanagement.response.CustomerOrderResponseVO;

public interface CustomerOrderService {
    CustomerOrderResponseVO makeOrder (CustomerOrderDto orderDto);
    String deleteOrder(Long id);
}
