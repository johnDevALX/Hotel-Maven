package com.ekene.hotelmanagement.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Email {
    private String to;
    private String subject;
    private String text;
}
