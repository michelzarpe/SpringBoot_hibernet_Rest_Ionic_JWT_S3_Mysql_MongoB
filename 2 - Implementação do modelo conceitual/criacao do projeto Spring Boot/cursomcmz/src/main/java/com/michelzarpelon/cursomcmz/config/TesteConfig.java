package com.michelzarpelon.cursomcmz.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.michelzarpelon.cursomcmz.services.DBService;

@Configuration
@Profile("teste")
public class TesteConfig {

	@Autowired
	private DBService dbservice;  
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbservice.instantiateTesteDatabase();
		return true;
	}
	
}
