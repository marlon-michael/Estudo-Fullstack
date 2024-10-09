package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.Filter;

import java.util.Arrays;
import java.util.Collections;

@EnableFeignClients
@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<Filter> corsFilter(){
		final UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // endereço da aplicação front-end
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "UPDATE", "DELETE", "OPTIONS", "PATCH"));
		src.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>(new CorsFilter(src));
		return registration;
	}

}

