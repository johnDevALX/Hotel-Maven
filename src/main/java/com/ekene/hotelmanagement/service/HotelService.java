package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.payload.ServiceBillingDto;
import com.ekene.hotelmanagement.payload.ServiceCategoryDto;
import com.ekene.hotelmanagement.response.ServiceBillingResponseVO;
import com.ekene.hotelmanagement.response.ServiceCategoryResponseVO;

public interface HotelService {
    ServiceCategoryResponseVO createServiceCategory(ServiceCategoryDto serviceCategoryDto);
    String deleteServiceCategory(Long id);
    ServiceBillingResponseVO createServiceBilling(ServiceBillingDto serviceBillingDto);
    ServiceBillingResponseVO updateServiceBilling(Long id, ServiceBillingDto serviceBillingDto);
    String deleteServiceBilling(Long id);
}
