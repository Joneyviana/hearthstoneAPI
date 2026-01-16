package com.api.hearthstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.api.hearthstone")
public class HearthstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HearthstoneApplication.class, args);
	}

}
