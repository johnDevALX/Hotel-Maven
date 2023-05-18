package com.ekene.hotelmanagement.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceCategory extends BaseModel{
    private String image;
    private String title;
}
