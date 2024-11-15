package com.journey_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JourneyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JourneyBackendApplication.class, args);
	}

}
