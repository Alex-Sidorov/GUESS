package com.guess.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AmazonConfiguration {

    private final AppConfiguration appConfiguration;

    @Bean
    public AmazonS3 amazonS3() {

        final AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(appConfiguration.getAmazon().getAccessKey(),
                        appConfiguration.getAmazon().getSecretKey()));

        final AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        builder.withCredentials(credentialsProvider);

        if (appConfiguration.getAmazon().getS3().getEndpoint() != null) {
            builder.withPathStyleAccessEnabled(true);
            builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                    appConfiguration.getAmazon().getS3().getEndpoint(),
                    appConfiguration.getAmazon().getS3().getRegion()));
        } else {
            builder.withRegion(appConfiguration.getAmazon().getS3().getRegion());
        }

        return builder.build();
    }

}
