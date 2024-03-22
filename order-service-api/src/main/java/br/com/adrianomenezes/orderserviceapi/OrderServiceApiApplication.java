package br.com.adrianomenezes.orderserviceapi;

import br.com.adrianomenezes.orderserviceapi.entities.Order;
import br.com.adrianomenezes.orderserviceapi.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApiApplication.class, args);
	}

}
