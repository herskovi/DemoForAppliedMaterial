package com.amat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages={"com.amat"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
@Configuration
@EnableJpaRepositories
@EnableCaching
public class Demo4AmatApplication {

	public static void main(String[] args) {
		SpringApplication.run(Demo4AmatApplication.class, args);
	}
}
