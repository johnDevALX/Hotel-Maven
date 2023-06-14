package com.ekene.hotelmanagement.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelAccount {
    static HotelAccount hotelAccount = new HotelAccount();

    private double hotelAccountBal = 0.0;

    public static HotelAccount getInstance(){
        return hotelAccount;
    }

    private HotelAccount(){}
}
