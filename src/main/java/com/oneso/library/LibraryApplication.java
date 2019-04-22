package com.oneso.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories("com.oneso.library.repository")
public class LibraryApplication {

    public static void main(String[] args) { SpringApplication.run(LibraryApplication.class, args); }

}
