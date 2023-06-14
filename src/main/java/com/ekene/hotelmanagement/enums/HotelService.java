package com.ekene.hotelmanagement.enums;

public enum HotelService {
    CATERING_SERVICES(15.0), LAUNDRY(8.0), SPA_SESSION(15.0), BAR_SERVICE(10.0);

    final Double price;

    public Double getPrice() {
        return price;
    }

    HotelService(Double price) {
        this.price = price;
    }

}
