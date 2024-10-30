package com.compass.aws_springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class AwsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSpringbootApplication.class, args);
	}

}
