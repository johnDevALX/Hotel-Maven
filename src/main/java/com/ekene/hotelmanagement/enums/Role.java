package com.ekene.hotelmanagement.enums;

public enum Role {
    CHEF(9.0), CLEANER(9.0), BARMAN(9.0), SPA_MANAGER(9.0), ADMIN(15.0),
    CUSTOMER(0.0);
    final Double salary;

    public Double getSalary() {
        return salary;
    }

    Role(Double salary) {
        this.salary = salary;
    }
}


