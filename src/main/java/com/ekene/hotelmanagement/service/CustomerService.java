package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.payload.CustomerDto;
import com.ekene.hotelmanagement.response.CustomerResponseVO;

public interface CustomerService {
    CustomerResponseVO saveCustomer(CustomerDto customerDto);
    String deleteCustomer(Long id);
}
