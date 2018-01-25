package com.customerservice.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan({"com.customerservice.callcenter"})
public class CallcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallcenterApplication.class, args);
	}
}
