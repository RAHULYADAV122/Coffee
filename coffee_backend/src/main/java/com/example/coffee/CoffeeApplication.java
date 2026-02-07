package com.example.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@org.springframework.scheduling.annotation.EnableScheduling
public class CoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeApplication.class, args);
	}

	@Bean
	public org.springframework.boot.CommandLineRunner demo(com.example.coffee.repo.WorkerRepository workerRepo, com.example.coffee.repo.CustomerRepository customerRepo) {
		return (args) -> {
			if (workerRepo.count() == 0) {
				workerRepo.save(new com.example.coffee.model.Worker(null, "admin", "admin", "MANAGER"));
				workerRepo.save(new com.example.coffee.model.Worker(null, "barista", "barista", "BARISTA"));
			}
            if (customerRepo.count() == 0) {
                customerRepo.save(new com.example.coffee.model.Customer(null, "John Doe", "john@example.com", true));
                customerRepo.save(new com.example.coffee.model.Customer(null, "Jane Smith", "jane@example.com", false));
            }
		};
	}

	@Bean
	public org.springframework.web.servlet.config.annotation.WebMvcConfigurer corsConfigurer() {
		return new org.springframework.web.servlet.config.annotation.WebMvcConfigurer() {
			@Override
			public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:5173")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}
}
