package com.exemplo.encomendaService;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class EncomendaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncomendaServiceApplication.class, args);
	}

}
