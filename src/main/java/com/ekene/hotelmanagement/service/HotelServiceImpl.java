package com.ekene.hotelmanagement.service;

import com.ekene.hotelmanagement.model.ServiceBilling;
import com.ekene.hotelmanagement.model.ServiceCategory;
import com.ekene.hotelmanagement.payload.ServiceBillingDto;
import com.ekene.hotelmanagement.payload.ServiceCategoryDto;
import com.ekene.hotelmanagement.repository.ServiceBillingRepository;
import com.ekene.hotelmanagement.repository.ServiceCategoryRepository;
import com.ekene.hotelmanagement.response.ServiceBillingResponseVO;
import com.ekene.hotelmanagement.response.ServiceCategoryResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService{
    private final ServiceBillingRepository serviceBillingRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

    @Override
    public ServiceCategoryResponseVO createServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        ServiceCategory serviceCategory = ServiceCategory.builder()
                .title(serviceCategoryDto.getTitle())
                .build();

        serviceCategoryRepository.save(serviceCategory);
        return mapToServiceCategoryResponse(serviceCategory);
    }

    @Override
    public String deleteServiceCategory(Long id) {
        String response = "";
        try{
            serviceCategoryRepository.deleteById(id);
            response = "Deleted Successfully";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(response);
        }
        return response;
    }

    @Override
    public ServiceBillingResponseVO createServiceBilling(ServiceBillingDto serviceBillingDto) {
        ServiceCategory serviceCategory = serviceCategoryRepository
                .findByTitleIgnoreCase(serviceBillingDto.getServiceCategory()).get();
        ServiceBilling serviceBilling = ServiceBilling.builder()
                .serviceCategory(serviceCategory)
                .itemName(serviceBillingDto.getItemName())
                .itemCost(serviceBillingDto.getItemCost())
                .build();

        serviceBillingRepository.save(serviceBilling);
        return mapToServiceBillingResponse(serviceBilling);
    }

    @Override
    public ServiceBillingResponseVO updateServiceBilling(Long id, ServiceBillingDto serviceBillingDto) {
        ServiceBilling serviceBilling = serviceBillingRepository.findById(id).get();
        ServiceCategory serviceCategory = serviceCategoryRepository
                .findByTitleIgnoreCase(serviceBillingDto.getServiceCategory()).get();
        serviceBilling.setServiceCategory(serviceCategory);
        serviceBilling.setItemName(serviceBillingDto.getItemName());
        serviceBilling.setItemCost(serviceBillingDto.getItemCost());

        serviceBillingRepository.save(serviceBilling);
        return mapToServiceBillingResponse(serviceBilling);
    }

    @Override
    public String deleteServiceBilling(Long id) {
        String response = "";
        try{
            serviceBillingRepository.deleteById(id);
            response = "Deleted Successfully";
        } catch (IllegalArgumentException e){
            response = e.getMessage();
            throw new IllegalArgumentException(response);
        }
        return response;
    }


    private ServiceCategoryResponseVO mapToServiceCategoryResponse(ServiceCategory serviceCategory){
        return ServiceCategoryResponseVO.builder()
                .title(serviceCategory.getTitle())
                .build();
    }

    private ServiceBillingResponseVO mapToServiceBillingResponse (ServiceBilling serviceBilling){
        return ServiceBillingResponseVO.builder()
                .itemName(serviceBilling.getItemName())
                .itemCost(serviceBilling.getItemCost())
                .build();
    }
}
