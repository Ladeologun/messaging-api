package com.xproj.simpleMessagingApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleMessagingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMessagingApiApplication.class, args);
	}

}
