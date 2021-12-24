package com.flight_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * main class
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
public class AdminServiceApp {

	/**
	 * main method
	 *
	 */
	public static void main(final String[] args) {
		SpringApplication.run(AdminServiceApp.class, args);
	}

}
