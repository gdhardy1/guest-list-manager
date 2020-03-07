package com.gdhardy.simplersvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan("com.gdhardy.simplersvp")
public class SimpleRsvpApp {

	public static void main(String[] args) {
    SpringApplication.run(SimpleRsvpApp.class, args);
  }
  
}
