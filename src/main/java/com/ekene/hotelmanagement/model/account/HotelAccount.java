package com.ekene.hotelmanagement.model.account;

import com.ekene.hotelmanagement.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelAccount extends BaseModel {
    private double hotelAccountBal;

//    static HotelAccount hotelAccount = new HotelAccount();
//
//    public static HotelAccount getInstance(){
//        return hotelAccount;
//    }
//
//    private HotelAccount(){}
}
