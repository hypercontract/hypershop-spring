package org.hypercontract.hypershop;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HypershopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypershopApplication.class, args);
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}

	@Bean
	protected Module jacksonHibernateModule() {
		var module = new Hibernate5Module();
		module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return module;
	}
}
