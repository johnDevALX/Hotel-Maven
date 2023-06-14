package com.ekene.hotelmanagement.model.account;

public class Main {
    public static void main(String[] args) {
        HotelAccount hotelAccount = HotelAccount.getInstance();

        System.out.println(hotelAccount.getHotelAccountBal());
    }
}
