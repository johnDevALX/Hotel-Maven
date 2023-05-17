package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.model.Address;
import com.ekene.hotelmanagement.model.Customer;
import com.ekene.hotelmanagement.payload.CustomerDto;
import com.ekene.hotelmanagement.repository.CustomerRepository;
import com.ekene.hotelmanagement.response.CustomerResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    @Override
    public CustomerResponseVO saveCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .DOB(customerDto.getDOB())
                .gender(customerDto.getGender())
                .mobile(customerDto.getMobile())
                .nationality(customerDto.getNationality())
                .address(buildAddress(customerDto))
                .build();
        customerRepository.save(customer);
        return mapToCustomerResponse(customer);
    }

    @Override
    public String deleteCustomer(Long id) {
        String response = "";
        try{
            customerRepository.deleteById(id);
            response = "Successfully Deleted";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(e.getMessage());
        }
        return response;
    }


    private Address buildAddress(CustomerDto customerDto){
        return   Address.builder()
                .street(customerDto.getStreet())
                .city(customerDto.getCity())
                .state(customerDto.getState())
                .postalCode(customerDto.getPostalCode())
                .country(customerDto.getCountry())
                .build();
    }
    private CustomerResponseVO mapToCustomerResponse(Customer customer){
        return CustomerResponseVO.builder()
                .name(customer.getFirstName())
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .build();
    }
}
