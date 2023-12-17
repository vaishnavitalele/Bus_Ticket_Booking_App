package com.example.BusTicketBookingApplication;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = {"com.example.repository"})
@EntityScan(basePackages = {"com.example.entity"})
@ComponentScan(basePackages = {"com.example.controller","com.example.service","com.example.service.impl","com.example.customexception","com.example.exceptionhandling","com.example.dto","com.example.mapper"})
@SpringBootApplication
public class BusTicketBookingApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BusTicketBookingApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
