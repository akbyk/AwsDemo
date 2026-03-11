package com.akbyk.awsdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.elasticbeanstalk.ElasticBeanstalkClient;
import software.amazon.awssdk.services.guardduty.GuardDutyClient;
import software.amazon.awssdk.services.iam.IamClient;

@Configuration
public class AWSConfig {

    @Bean
    public IamClient iamClient() {
        return IamClient.builder()
                .region(Region.AWS_GLOBAL) // IAM global servistir, region yok
                .build();
    }

    @Bean
    public GuardDutyClient guardDutyClient() {
        return GuardDutyClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }


    @Bean
    public ElasticBeanstalkClient beanstalkClient() {
        return ElasticBeanstalkClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}