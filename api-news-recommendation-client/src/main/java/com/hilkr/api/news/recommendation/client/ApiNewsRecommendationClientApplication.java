package com.hilkr.api.news.recommendation.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.hilkr.api.news.recommendation.client.config",
        "com.hilkr.api.news.recommendation.client.controller",
        "com.hilkr.api.news.recommendation.client.service"
})
public class ApiNewsRecommendationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNewsRecommendationClientApplication.class, args);
    }

}

