package com.ekene.hotelmanagement.model.hotel;

import com.ekene.hotelmanagement.model.BaseModel;
import com.ekene.hotelmanagement.model.Users;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Booking extends BaseModel {
    @OneToOne(cascade = CascadeType.ALL)
    private Users user;
    @OneToOne(cascade = CascadeType.ALL)
    private Room room;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int totalDays;

//    static {
//        this.fromDate.getDayOfWeek().getValue();
//    }
}
