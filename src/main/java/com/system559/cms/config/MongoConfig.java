package com.system559.cms.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private final String DB_NAME = "cms";
    private final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    public @Bean MongoClient mongoClient() {
        logger.info(String.format("MongoUri: %s ", System.getenv("cmsMongoUri")));
        return MongoClients.create(System.getenv("cmsMongoUri"));
    }
}
