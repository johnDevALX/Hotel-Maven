package com.ekene.hotelmanagement.response;

import com.ekene.hotelmanagement.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseVO {
    private String name;
    private String email;
    private Role role;
    private String token;
}
