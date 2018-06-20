package com.michelzarpelon.cursomcmz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.michelzarpelon.cursomcmz.domain.Categoria;
import com.michelzarpelon.cursomcmz.repositories.CategoriaRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.DataIntegrityException;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorioCategoria;

	public Categoria find(Integer id) {
		Categoria categoria = repositorioCategoria.findOne(id);
		if (categoria == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado: " + id + ", Tipo do objeto: " + Categoria.class.getName());
		}
		return categoria;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorioCategoria.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repositorioCategoria.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repositorioCategoria.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Objeto não pode ser deletado: " + id + ", Tipo do objeto: "
					+ Categoria.class.getName() + ", pois possui produtos");
		}
	}

}
