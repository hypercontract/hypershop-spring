package org.hypercontract.hypershop;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
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

	@Bean
	protected Module jacksonHibernateModule() {
		var module = new Hibernate5Module();
		module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return module;
	}
}
