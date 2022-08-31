package com.hunny.uneasySolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UneasySolverApplication {

	public static void main(String[] args) {

		SpringApplication.run(UneasySolverApplication.class, args);
	}

}
