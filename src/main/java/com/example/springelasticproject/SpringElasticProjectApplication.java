package com.example.springelasticproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "*")
public class SpringElasticProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringElasticProjectApplication.class, args);
    }

}
