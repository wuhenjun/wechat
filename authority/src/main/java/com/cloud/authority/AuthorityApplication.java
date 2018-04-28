package com.cloud.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan
@EnableAsync
public class AuthorityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorityApplication.class, args);
	}
}
