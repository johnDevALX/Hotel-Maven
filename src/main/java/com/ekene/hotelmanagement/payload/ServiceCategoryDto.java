package com.ekene.hotelmanagement.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceCategoryDto {
    private Long id;
    private String image;
    private String title;
}
