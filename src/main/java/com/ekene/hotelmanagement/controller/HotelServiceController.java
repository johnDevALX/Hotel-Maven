package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.ServiceBillingDto;
import com.ekene.hotelmanagement.payload.ServiceCategoryDto;
import com.ekene.hotelmanagement.service.hotel.HotelServiceImpl;
import com.ekene.hotelmanagement.utility.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/hotel-service")
public class HotelServiceController extends BaseController {
    private final HotelServiceImpl hotelService;

    @PostMapping("addServiceCategory")
    public ResponseEntity<?> addServiceCategory(@RequestBody ServiceCategoryDto serviceCategoryDto){
        return getAppResponse(CREATED, "Success", hotelService.createServiceCategory(serviceCategoryDto));
    }

    @Transactional
    @DeleteMapping("deleteServiceCategory/{id}")
    public ResponseEntity<?> deleteServiceCategory (@PathVariable(value = "id") Long id){
        return getAppResponse(OK, "deleted!", hotelService.deleteServiceCategory(id));
    }

    @PostMapping("addServiceBilling")
    public ResponseEntity<?> addServiceBilling(@RequestBody ServiceBillingDto serviceBillingDto){
        return getAppResponse(CREATED, "Success", hotelService.createServiceBilling(serviceBillingDto));
    }

    @PutMapping("updateServiceBilling/{id}")
    public ResponseEntity<?> updateServiceBilling (@PathVariable(value = "id") Long id, @RequestBody ServiceBillingDto serviceBillingDto){
        return getAppResponse(OK, "Updated", hotelService.updateServiceBilling(id, serviceBillingDto));
    }

    @Transactional
    @DeleteMapping("deleteServiceBilling/{id}")
    public ResponseEntity<?> deleteServiceBilling (@PathVariable(value = "id") Long id){
        return getAppResponse(OK, "Deleted!", hotelService.deleteServiceBilling(id));
    }
}
