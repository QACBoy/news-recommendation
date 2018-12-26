package com.hilkr.api.news.recommendation.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hilkr.api.news.recommendation.client.dal.dao")
public class ApiNewsRecommendationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNewsRecommendationClientApplication.class, args);
    }

}

