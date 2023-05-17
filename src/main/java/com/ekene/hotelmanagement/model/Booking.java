package com.ekene.hotelmanagement.model;

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
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private Room room;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int totalDays;

//    static {
//        this.fromDate.getDayOfWeek().getValue();
//    }
}
