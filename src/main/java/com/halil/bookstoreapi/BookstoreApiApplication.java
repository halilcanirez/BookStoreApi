package com.halil.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class BookstoreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApiApplication.class, args);
    }
}
