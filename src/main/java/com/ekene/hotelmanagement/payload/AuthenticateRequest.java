package com.ekene.hotelmanagement.payload;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {
    private String email;
    String password;
}
