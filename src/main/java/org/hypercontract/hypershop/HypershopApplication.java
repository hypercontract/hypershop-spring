package org.hypercontract.hypershop;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.github.javafaker.Faker;
import org.hypercontract.hypershop.http.RequestBodyMappingHandlerMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.cors.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL_FORMS;

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

//	@Bean
//	public WebMvcRegistrations webMvcRegistrations() {
//		return new WebMvcRegistrations() {
//
//			@Override
//			public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//				return new RequestBodyMappingHandlerMapping();
//			}
//		};
//	}
}
