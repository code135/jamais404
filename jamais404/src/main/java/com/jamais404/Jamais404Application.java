package com.jamais404;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class })
public class Jamais404Application {

	public static void main(String[] args) {
		SpringApplication.run(Jamais404Application.class, args);
	}

}
