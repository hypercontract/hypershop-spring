package org.hypercontract.hypershop;

import org.hypercontract.hypershop.mock.MockData;
import org.hypercontract.hypershop.mock.MockDataFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HypershopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypershopApplication.class, args);
	}

	@Bean
    public MockData mockData(MockDataFactory mockDataFactory) {
	    return mockDataFactory.createMockData();
    }
}
