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

    @Data
    public static class CloudConfig{
        private String apiKey;
        private String cloudName;
        private String apiSecret;
    }
}
