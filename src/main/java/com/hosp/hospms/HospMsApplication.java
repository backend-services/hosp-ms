package com.hosp.hospms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class HospMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospMsApplication.class, args);
	}

}
