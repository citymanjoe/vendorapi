package com.wemabank.vendorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VendorapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorapiApplication.class, args);
	}

}
