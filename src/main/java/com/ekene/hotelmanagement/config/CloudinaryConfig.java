package com.ekene.hotelmanagement.config;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Autowired
    private AppConfig appConfig;

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", appConfig.getCloudConfig().getCloudName());
        config.put("api_key", appConfig.getCloudConfig().getApiKey());
        config.put("api_secret", appConfig.getCloudConfig().getApiSecret());
        return new Cloudinary(config);
    }
}
