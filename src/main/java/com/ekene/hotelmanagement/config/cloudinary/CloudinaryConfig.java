package com.ekene.hotelmanagement.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.ekene.hotelmanagement.config.cloudinary.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
