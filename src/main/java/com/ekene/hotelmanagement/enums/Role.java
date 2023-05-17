package com.ekene.hotelmanagement.enums;

public enum Role {
    CHEF(2000.0), CLEANER(1500.0), BARMAN(2200.0), SPA_MANAGER(2500.0), ADMIN(5000.0),
    CUSTOMER(0.0);
    final Double salary;
    Role(Double salary) {
        this.salary = salary;
    }
}


