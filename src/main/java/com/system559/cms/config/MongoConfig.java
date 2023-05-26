package com.system559.cms.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.lang.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * MongoClient configuration bean
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    /**
     * Returns the name of the database we're connecting to
     * @return string containing the database name
     */
    @Override
    @NonNull
    protected String getDatabaseName() {
        return "cms";
    }

    /**
     * Returns a new MongoClient
     * @return new MongoClient instance
     */
    @NonNull
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(System.getenv("cmsMongoUri"));
    }
}
