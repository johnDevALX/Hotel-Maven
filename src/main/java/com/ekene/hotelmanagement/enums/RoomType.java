package com.ekene.hotelmanagement.enums;

public enum RoomType {
    STANDARD(9999.99), CLASSIC(12499.99), DELUXE(14999.99), EXECUTIVE(19999.99), PRESIDENTIAL(24999.99);
    final Double cost;

    RoomType(Double cost) {
        this.cost = cost;
    }
}
