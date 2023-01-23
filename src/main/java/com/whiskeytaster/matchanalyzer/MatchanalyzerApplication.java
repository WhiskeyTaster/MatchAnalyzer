package com.whiskeytaster.matchanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.whiskeytaster.matchanalyzer")
public class MatchanalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchanalyzerApplication.class, args);
	}

}
