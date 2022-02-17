package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({SwaggerConfig.class})
@SpringBootApplication
public class BiBilsemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiBilsemApplication.class, args);
	}

}
