package com.ekene.hotelmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class ServiceBilling extends BaseModel{
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private ServiceCategory serviceCategory;
    private String itemName;
    private Double itemCost;
}
