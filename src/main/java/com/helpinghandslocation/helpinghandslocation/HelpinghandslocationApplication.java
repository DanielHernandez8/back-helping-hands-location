package com.helpinghandslocation.helpinghandslocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@ComponentScan(basePackages = "com.helpinghandslocation")
public class HelpinghandslocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpinghandslocationApplication.class, args);
	}

}
