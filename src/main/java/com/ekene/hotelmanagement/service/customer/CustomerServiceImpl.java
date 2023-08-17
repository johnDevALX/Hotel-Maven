package com.ekene.hotelmanagement.service.customer;

import com.ekene.hotelmanagement.enums.Role;
import com.ekene.hotelmanagement.model.Address;
import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.payload.CustomerDto;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.response.UserResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;
//    private final JwtUtil jwtUtil;
    @Override
    public UserResponseVO saveCustomer(CustomerDto customerDto) {
        Users user = Users.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .DOB(customerDto.getDOB())
                .gender(customerDto.getGender())
                .role(Role.CUSTOMER)
                .mobile(customerDto.getMobile())
                .nationality(customerDto.getNationality())
                .address(buildAddress(customerDto))
                .build();
        userRepository.save(user);
        return mapToUserResponse(user);
    }

    @Override
    public String deleteCustomer(Long id) {
        String response = "";
        try{
            userRepository.deleteById(id);
            response = "Successfully Deleted";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(response);
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
//    private CustomerResponseVO mapToCustomerResponse(Customer customer){
//        return CustomerResponseVO.builder()
//                .name(customer.getFirstName())
//                .email(customer.getEmail())
//                .mobile(customer.getMobile())
//                .build();
//    }

    private UserResponseVO mapToUserResponse(Users user){
//        var jwkToken = jwtUtil.generateToken(user.getEmail());
        return UserResponseVO.builder()
                .name(user.getFirstName())
                .email(user.getEmail())
                .role(user.getRole())
//                .token(jwkToken)
                .build();
    }
}
