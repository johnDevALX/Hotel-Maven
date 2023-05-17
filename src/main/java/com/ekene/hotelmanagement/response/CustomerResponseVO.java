package com.ekene.hotelmanagement.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CustomerResponseVO {
    private String name;
    private String email;
    private Long mobile;
}
