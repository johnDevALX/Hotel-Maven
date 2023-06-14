package com.ekene.hotelmanagement.enums;

public enum RoomType {
    STANDARD(299.99), CLASSIC(399.99), DELUXE(499.99), EXECUTIVE(599.99), PRESIDENTIAL(699.99);
    final Double cost;

    public Double getCost() {
        return cost;
    }
    RoomType(Double cost) {
        this.cost = cost;
    }
}
