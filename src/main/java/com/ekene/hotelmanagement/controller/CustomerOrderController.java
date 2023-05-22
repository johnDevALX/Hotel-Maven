package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.CustomerOrderDto;
import com.ekene.hotelmanagement.service.CustomerOrderServiceImpl;
import com.ekene.hotelmanagement.utility.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/order")
public class CustomerOrderController extends BaseController {
    private final CustomerOrderServiceImpl orderService;

    @PostMapping("makeOrder")
    public ResponseEntity<?> makeOrder (@RequestBody CustomerOrderDto orderDto){
        return getAppResponse(CREATED, "success", orderService.makeOrder(orderDto));
    }

    @Transactional
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOrder (@PathVariable(value = "id") Long id){
        return getAppResponse(OK, "deleted!", orderService.deleteOrder(id));
    }
}
