package com.gdhardy.simplersvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gdhardy.simplersvp")
public class SimpleRsvpApp {

	public static void main(String[] args) {
    SpringApplication.run(SimpleRsvpApp.class, args);
  }
  
}
