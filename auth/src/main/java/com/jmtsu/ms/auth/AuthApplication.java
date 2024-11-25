package com.jmtsu.ms.auth;

import com.jmtsu.ms.auth.config.AuthConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.jmtsu.ms.core.model"})
@EnableJpaRepositories({"com.jmtsu.ms.core.repository"})
//@EnableConfigurationProperties(value = AuthConfig.class)
@ComponentScan("com.jmtsu.ms")
@EnableDiscoveryClient
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
