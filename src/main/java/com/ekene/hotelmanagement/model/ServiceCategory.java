package com.ekene.hotelmanagement.model;

import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class ServiceCategory extends BaseModel{
    private String title;
}
