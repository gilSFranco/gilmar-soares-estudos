package com.compasso.msnotify;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MsnotifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsnotifyApplication.class, args);
	}

}
