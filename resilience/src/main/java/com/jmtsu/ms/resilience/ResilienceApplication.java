package com.jmtsu.ms.resilience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.jmtsu.ms.core.model"})
@EnableJpaRepositories({"com.jmtsu.ms.core.repository"})
public class ResilienceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResilienceApplication.class, args);
	}

}
