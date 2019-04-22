package com.oneso.library.mongoConfig;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import com.oneso.library.configDB.MongoProps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public Mongock mongock(MongoClient client) {
        return new SpringMongockBuilder(client, "test", "com.oneso.lobrary.mongoConfig.changelog")
                .setLockQuickConfig()
                .build();
    }
}
