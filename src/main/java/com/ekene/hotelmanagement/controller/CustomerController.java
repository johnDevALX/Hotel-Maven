package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.CustomerDto;
import com.ekene.hotelmanagement.service.CustomerServiceImpl;
import com.ekene.hotelmanagement.utility.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseController {
    private final CustomerServiceImpl customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customerDto){
        return getAppResponse(HttpStatus.CREATED, "Success", customerService.saveCustomer(customerDto));
    }
}
