package com.nick.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import java.util.Random;
import java.util.function.Consumer;

@SpringBootApplication
@EnableLoadTimeWeaving
@EnableSpringConfigured
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public Consumer<Loan> log() {
		return person -> {
			Random r=new Random();
			try {
				Thread.sleep(r.nextInt(10)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Received: " + person);
		};
	}

}
