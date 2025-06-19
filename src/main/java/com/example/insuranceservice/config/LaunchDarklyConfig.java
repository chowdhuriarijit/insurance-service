package com.example.insuranceservice.config;

import com.launchdarkly.sdk.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class LaunchDarklyConfig {

    @Value("${launchdarkly.sdk.key}")
    private String sdkKey;

    @Bean
    public LDClient ldClient() {
        return new LDClient(sdkKey);
    }
}



