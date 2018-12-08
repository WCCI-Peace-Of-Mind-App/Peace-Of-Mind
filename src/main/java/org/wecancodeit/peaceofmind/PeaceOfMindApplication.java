package org.wecancodeit.peaceofmind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PeaceOfMindApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(PeaceOfMindApplication.class, args);
	}
}
