package com.github.jferrater.petprofilesservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author joffryferrater
 */
@SpringBootApplication
public class PetprofilesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetprofilesServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
