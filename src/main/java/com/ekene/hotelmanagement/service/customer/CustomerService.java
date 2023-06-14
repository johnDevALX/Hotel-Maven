package com.ekene.hotelmanagement.service.customer;

import com.ekene.hotelmanagement.payload.CustomerDto;
import com.ekene.hotelmanagement.response.CustomerResponseVO;
import com.ekene.hotelmanagement.response.UserResponseVO;

public interface CustomerService {
    UserResponseVO saveCustomer(CustomerDto customerDto);
    String deleteCustomer(Long id);
}
