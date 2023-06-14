package com.ekene.hotelmanagement.config.cloudinary;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {
    private CloudConfig cloudConfig;
    private FlutterConfig flutterConfig;

    @Data
    public static class CloudConfig{
        private String apiKey;
        private String cloudName;
        private String apiSecret;
    }

    @Data
    public static class FlutterConfig{
        private String redirectUrl;
        private String initUrl;
        private String verifyUrl;
        private String publicKey;
        private String secretKey;
    }
}
