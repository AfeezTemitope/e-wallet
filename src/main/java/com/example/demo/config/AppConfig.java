package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
@Getter
@Setter
@ToString
public class AppConfig {

    @Value("${PAYSTACK_API_KEY}")
    private String payStackApiKey;

    @Value("${PAYSTACK_URL}")
    private String payStackUrl;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
