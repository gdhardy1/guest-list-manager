package com.gdhardy.simplersvp.configuration;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClientSettings;

/**
 * MongoConfig
 */
@Configuration
public class MongoConfig{

  @Value("${mongodb.uri}")
  private String MONGO_URI;
  @Value("${mongodb.database}")
  private String MONGO_DB_NAME;

  public @Bean MongoClient configure(){
    ConnectionString connString = new ConnectionString(MONGO_URI);

    MongoClientSettings settings = MongoClientSettings.builder()
      .applyConnectionString(connString)
      .retryWrites(true)
      .build();

    return MongoClients.create(settings);
  }

  public @Bean MongoTemplate mongoTemplate(){
    return new MongoTemplate(configure(), MONGO_DB_NAME);
  }
}