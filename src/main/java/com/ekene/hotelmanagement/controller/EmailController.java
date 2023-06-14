package com.ekene.hotelmanagement.controller;

import com.ekene.hotelmanagement.payload.Email;
import com.ekene.hotelmanagement.service.email.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailServiceImpl emailService;

    @PostMapping("send-email")
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
        emailService.sendEmail(email);
        return ResponseEntity.ok("Email Sent");
    }
}
