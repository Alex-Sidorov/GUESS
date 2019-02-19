package com.guess.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class AppConfiguration {

    private Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Jwt {

        private String base64Secret;

        private long accessTokenValidity;
        private long refreshTokenValidity;
    }

}
