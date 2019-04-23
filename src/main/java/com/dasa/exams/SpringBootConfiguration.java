package com.dasa.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.dasa.exams" })
public class SpringBootConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfiguration.class, args);
	}

}
