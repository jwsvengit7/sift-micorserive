package com.sifts.Sifts.Notification.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SiftsNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiftsNotificationServiceApplication.class, args);
	}

}
