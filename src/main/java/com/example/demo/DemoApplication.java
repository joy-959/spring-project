package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			// 데이터가 이미 존재하는지 확인한 후 데이터 삽입
			if (!repository.existsById(1L)) {
				repository.save(new Product("Example Product 1", "This is an example product description 1.", 19.99, LocalDate.of(2024, 1, 1)));
			}
			if (!repository.existsById(2L)) {
				repository.save(new Product("Example Product 2", "This is an example product description 2.", 29.99, LocalDate.of(2024, 2, 1)));
			}
		};
	}
}