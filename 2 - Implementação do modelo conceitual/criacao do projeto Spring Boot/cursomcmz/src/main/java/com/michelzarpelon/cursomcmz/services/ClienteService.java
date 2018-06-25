package com.michelzarpelon.cursomcmz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.michelzarpelon.cursomcmz.domain.Cliente;
import com.michelzarpelon.cursomcmz.domain.Cliente;
import com.michelzarpelon.cursomcmz.domain.dto.ClienteDTO;
import com.michelzarpelon.cursomcmz.repositories.ClienteRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.DataIntegrityException;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorioObj;

	public Cliente find(Integer id) {
		Cliente cliente = repositorioObj.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado: " + id + ", Tipo do objeto: " + Cliente.class.getName());
		}
		return cliente;
	}

	public Cliente update(Cliente obj) {
		 Cliente objBanco = find(obj.getId());
		 obj.setCpfOuCnpj(objBanco.getCpfOuCnpj());
		 obj.setTipo(objBanco.getTipo());
		return repositorioObj.save(obj);
	}


	public void delete(Integer id) {
		find(id);
		try {
			repositorioObj.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Objeto não pode ser deletado: " + id + ", Tipo do objeto: "
					+ Cliente.class.getName() + ", pois possui Entidades Relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repositorioObj.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorioObj.findAll(pageRequest);
	}

	/* converter ObrjetoDTO para objeto normal */
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

}
