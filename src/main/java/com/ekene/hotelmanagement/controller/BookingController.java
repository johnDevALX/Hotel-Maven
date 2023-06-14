package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.BookingDto;
import com.ekene.hotelmanagement.service.hotel.BookingServiceImpl;
import com.ekene.hotelmanagement.utility.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/booking")
public class BookingController extends  BaseController{
    private final BookingServiceImpl bookingServiceImpl;

    @PostMapping("save-booking")
    public ResponseEntity<?> saveBooking(@RequestBody BookingDto bookingDto){
        return getAppResponse(CREATED , "success" , bookingServiceImpl.saveBooking(bookingDto));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllBookings(){
        return getAppResponse(OK, "Retrieved", bookingServiceImpl.getAllBookings());
    }
}
