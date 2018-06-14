package com.michelzarpelon.cursomcmz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelzarpelon.cursomcmz.domain.Categoria;
import com.michelzarpelon.cursomcmz.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	public Categoria buscar(Integer id) {
		Categoria categoria = repositorioCategoria.findOne(id);
		return categoria;
	}
	
	
}
