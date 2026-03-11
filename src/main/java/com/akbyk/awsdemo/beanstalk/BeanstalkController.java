package com.akbyk.awsdemo.beanstalk;

import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.elasticbeanstalk.ElasticBeanstalkClient;
import software.amazon.awssdk.services.elasticbeanstalk.model.*;

import java.util.List;

@RestController
@RequestMapping("/beanstalk")
public class BeanstalkController {

    private final ElasticBeanstalkClient beanstalkClient;

    public BeanstalkController(ElasticBeanstalkClient beanstalkClient) {
        this.beanstalkClient = beanstalkClient;
    }

    // List all environments
    @GetMapping("/environments")
    public List<String> listEnvironments() {
        return beanstalkClient.describeEnvironments()
                .environments()
                .stream()
                .map(EnvironmentDescription::environmentName)
                .toList();
    }

    // Get environment health
    @GetMapping("/environments/{envName}/health")
    public String getHealth(@PathVariable String envName) {
        return beanstalkClient.describeEnvironments(
                        DescribeEnvironmentsRequest.builder()
                                .environmentNames(envName)
                                .build())
                .environments()
                .get(0)
                .healthAsString();
    }

    // List application versions
    @GetMapping("/versions")
    public List<String> listVersions() {
        return beanstalkClient.describeApplicationVersions()
                .applicationVersions()
                .stream()
                .map(ApplicationVersionDescription::versionLabel)
                .toList();
    }
}