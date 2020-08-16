package com.ace.h2dbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("com.ace.h2dbdemo.entity*")
@ComponentScan("com.ace.h2dbdemo")
@EnableSwagger2
public class H2DbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2DbDemoApplication.class, args);
	}

}
