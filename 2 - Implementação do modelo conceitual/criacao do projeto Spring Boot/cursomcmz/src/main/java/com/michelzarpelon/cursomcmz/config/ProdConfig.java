package com.michelzarpelon.cursomcmz.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.michelzarpelon.cursomcmz.services.DBService;

@Configuration
@Profile("dev")
public class ProdConfig {

	@Autowired
	private DBService dbservice;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String estrategia;

	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!estrategia.equals("create")) { //pega informacao do arquivo application.properties, quando for igual insntancia todas as informacoes
			return false;
		}
		dbservice.instantiateTesteDatabase();
		return true;
	}

}
