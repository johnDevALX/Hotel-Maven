package com.ekene.hotelmanagement.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoginDto {
    private String email;
    private String password;
}
