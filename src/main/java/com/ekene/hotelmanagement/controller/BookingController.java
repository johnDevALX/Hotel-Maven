package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.response.ApiResponse;
import com.ekene.hotelmanagement.response.BookingResponseVO;
import com.ekene.hotelmanagement.service.BookingService;
import com.ekene.hotelmanagement.service.BookingServiceImpl;
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
@RequestMapping("api/booking")
public class BookingController extends  BaseController{
    private final BookingServiceImpl bookingServiceImpl;


    @PostMapping
    public ResponseEntity<?> saveBooking(@RequestBody BookingDto bookingDto){
        return getAppResponse(HttpStatus.CREATED , "success" , bookingServiceImpl.saveBooking(bookingDto));
    }
}
