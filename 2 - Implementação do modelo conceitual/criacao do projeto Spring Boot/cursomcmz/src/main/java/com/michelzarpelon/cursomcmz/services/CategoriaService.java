package com.michelzarpelon.cursomcmz.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelzarpelon.cursomcmz.domain.Categoria;
import com.michelzarpelon.cursomcmz.repositories.CategoriaRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	public Categoria buscar(Integer id) {
		Categoria categoria = repositorioCategoria.findOne(id);
		if(categoria==null) {
			throw new ObjectNotFoundException("Objeto não encontrado: "+id+", Tipo do objeto: "+Categoria.class.getName());
		}
		return categoria;
	}
	
	
}
