package com.ekene.hotelmanagement.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentReturnResponse implements Serializable {
    private PaymentData data;
//    private List<Map<String, Object>> data;
}
