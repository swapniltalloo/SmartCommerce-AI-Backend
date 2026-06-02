package com.swapnil.smartcommerce;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
@EnableMethodSecurity
@SpringBootApplication
@EnableCaching
public class SmartcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartcommerceApplication.class, args);
	}

}
