package com.pjq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyForumApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MyForumApplication.class, args);
	}

}
