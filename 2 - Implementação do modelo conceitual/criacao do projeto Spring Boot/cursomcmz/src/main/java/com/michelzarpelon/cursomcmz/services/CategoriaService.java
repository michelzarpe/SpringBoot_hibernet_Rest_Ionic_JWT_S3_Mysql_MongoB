package com.michelzarpelon.cursomcmz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.michelzarpelon.cursomcmz.domain.Categoria;
import com.michelzarpelon.cursomcmz.domain.dto.CategoriaDTO;
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

	public List<Categoria> findAll() {
		return repositorioCategoria.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorioCategoria.findAll(pageRequest);
	}
	
	/*converter ObrjetoDTO para objeto normal*/
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
	

}
