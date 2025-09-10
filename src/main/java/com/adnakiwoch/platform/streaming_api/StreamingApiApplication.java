package com.adnakiwoch.platform.streaming_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class StreamingApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(StreamingApiApplication.class, args);
  }
}
