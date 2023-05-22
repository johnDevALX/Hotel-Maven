package com.ekene.hotelmanagement.enums;

public enum Role {
    CHEF(20.0), CLEANER(15.0), BARMAN(22.0), SPA_MANAGER(25.0), ADMIN(50.0),
    CUSTOMER(0.0);
    final Double salary;

    public Double getSalary() {
        return salary;
    }

    Role(Double salary) {
        this.salary = salary;
    }
}


