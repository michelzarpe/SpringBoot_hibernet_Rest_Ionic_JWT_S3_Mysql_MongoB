package com.michelzarpelon.cursomcmz;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michelzarpelon.cursomcmz.domain.Categoria;
import com.michelzarpelon.cursomcmz.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcmzApplication implements CommandLineRunner{

	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcmzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		repositorioCategoria.save(Arrays.asList(cat1,cat2));
	}
}

/*cria tudo sozinho*/