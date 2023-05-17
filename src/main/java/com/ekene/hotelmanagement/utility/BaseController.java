package com.ekene.hotelmanagement.utility;

import com.ekene.hotelmanagement.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


public class BaseController {


    public ResponseEntity<?> getAppResponse( HttpStatus status , String message , Object data ){
        ApiResponse<Object> apiResponse = new ApiResponse<>(message , LocalDateTime.now() , data );
        return  new ResponseEntity<>( apiResponse , status);
    }
}
