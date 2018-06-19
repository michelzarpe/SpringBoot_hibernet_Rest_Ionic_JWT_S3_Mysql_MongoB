package com.michelzarpelon.cursomcmz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michelzarpelon.cursomcmz.domain.Cliente;
import com.michelzarpelon.cursomcmz.repositories.ClienteRepository;
import com.michelzarpelon.cursomcmz.services.execeptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	public Cliente buscar(Integer id) {
		Cliente cliente = repositorioCliente.findOne(id);
		if(cliente==null) {
			throw new ObjectNotFoundException("Objeto não encontrado: "+id+", Tipo do objeto: "+Cliente.class.getName());
		}
		return cliente;
	}
	
	
}
