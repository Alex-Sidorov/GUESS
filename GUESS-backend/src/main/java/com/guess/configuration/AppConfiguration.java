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
    private Amazon amazon = new Amazon();

    @Getter
    @Setter
    public static class Jwt {

        private String base64Secret;

        private long accessTokenValidity;
        private long refreshTokenValidity;

    }

    @Getter
    @Setter
    public static class Amazon {

        private String accessKey;
        private String secretKey;

        private S3 s3 = new S3();
        private CloudFront cloudFront = new CloudFront();

        @Getter
        @Setter
        public static class S3 {

            private String endpoint;
            private String region;
            private String imagesBucketName;

        }

        @Getter
        @Setter
        public static class CloudFront {

            private String imagesUrl;

        }

    }

}
