package com.bitpace.coincalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CoinCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinCalculatorApplication.class, args);
	}
}
