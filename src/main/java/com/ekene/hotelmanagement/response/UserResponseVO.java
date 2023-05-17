package com.ekene.hotelmanagement.response;

import com.ekene.hotelmanagement.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserResponseVO {
    private String name;
    private String email;
    private Role role;
}
