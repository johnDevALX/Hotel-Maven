package com.ekene.hotelmanagement.service.email;

import com.ekene.hotelmanagement.payload.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;


    @Override
    public void sendEmail(Email email) {
//        try {
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setFrom("jjohn999118@gmail.com");
//            messageHelper.setTo(email.getTo());
//            messageHelper.setSubject(email.getSubject());
//            messageHelper.setText(email.getText());
//
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("jjohn999118@gmail.com");
        mailMessage.setTo(email.getTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getText());

        javaMailSender.send(mailMessage);
    }
}
