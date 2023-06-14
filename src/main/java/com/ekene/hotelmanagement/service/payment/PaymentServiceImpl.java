package com.ekene.hotelmanagement.service.payment;

import com.ekene.hotelmanagement.config.cloudinary.AppConfig;
import com.ekene.hotelmanagement.model.Customer;
import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.payload.PaymentRequest;
import com.ekene.hotelmanagement.repository.UserRepository;
import com.ekene.hotelmanagement.response.payment.PaymentInitResponse;
import com.ekene.hotelmanagement.response.payment.PaymentReturnResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final UserRepository userRepository;

    @Override
    public Map<String, Object> initialize(PaymentRequest paymentRequest){
        Users user = userRepository.findByEmailIgnoreCase(paymentRequest.getEmail()).get();
        String url = appConfig.getFlutterConfig().getInitUrl();
        HttpMethod httpMethod = HttpMethod.POST;
        String txRef = RandomStringUtils.randomAlphabetic(7);
        Map<String, Object> payload = new HashMap<>();
        payload.put("tx_ref", txRef);
        payload.put("amount", paymentRequest.getAmount());
        payload.put("currency", paymentRequest.getCurrency());
        payload.put("redirect_url", appConfig.getFlutterConfig().getRedirectUrl());
        payload.put("customer", new Customer(user.getFirstName(),
                user.getEmail(), user.getMobile()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(appConfig.getFlutterConfig().getSecretKey());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<>() {
        };
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RequestEntity<?> requestEntity = new RequestEntity<>(payload, httpHeaders, httpMethod, uri);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                requestEntity, typeRef);
        System.out.println("Response from payment Service IMPL " + response);
//        restTemplate.postForObject(uri, response.getBody(), PaymentInitResponse.class);
        return response.getBody();

    }

    @Override
    public PaymentReturnResponse verifyTransaction(int transactionId) {
        String url = appConfig.getFlutterConfig().getVerifyUrl() + "/" + transactionId + "/verify";
        HttpMethod httpMethod = HttpMethod.GET;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(appConfig.getFlutterConfig().getSecretKey());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<>() {
        };
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RequestEntity<?> requestEntity = new RequestEntity<>(httpHeaders, httpMethod, uri);
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, typeRef);
        PaymentReturnResponse returnResponse = getObjectFromJson(getDecodedResponse(Objects.requireNonNull(response.getBody())), PaymentReturnResponse.class);
//        System.out.println("Response from payment Service IMPL " + response);
//        restTemplate.postForObject(uri, response.getBody(), PaymentInitResponse.class);
        System.out.println(returnResponse);
        return returnResponse;
    }


    protected String getDecodedResponse(String response) {
        String decodedResponse = response;
        if (!response.startsWith("{") && !response.startsWith("[")) {
            decodedResponse = new String(Base64.getDecoder().decode(response));
        }
        return decodedResponse;
    }


    public <T> T getObjectFromJson(String json, Class<T> clazz) throws IllegalStateException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private String hashSHA256(String originalString) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

//    private void addRaveDetailsToPayload(PaymentRequest paymentRequest) {
//        String txRef = UUID.randomUUID().toString();
//        paymentRequest.setPublicKey(ravePublicKey);
//        paymentRequest.setTxRef(txRef);
//        paymentRequest.setRedirectUrl("/callback");
//    }
}
